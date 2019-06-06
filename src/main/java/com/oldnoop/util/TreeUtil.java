package com.oldnoop.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 树形数据封装工具类
 * 用于将数据表中取出的所有数据,
 * 转换成需要的树形结构的数据
 * </pre>
 * @author oldnoop
 *
 */
public class TreeUtil {
	
	/**
	 * 
	 * @param data
	 *            原来的集合数据
	 * @param idFieldName
	 *            id对应的属性名称或者key名称
	 * @param textFieldName
	 *            text对应的属性名称或者key名称
	 * @param pidFieldName
	 *            pid对应的属性名称或者key名称
	 * @param parendIdValue
	 *            上级父节点的id值
	 * @param treeNodeId
	 *            树节点id名称
	 * @param treeNodeText
	 *            树节点文本名称
	 * @param treeNodeAttr
	 *            树节点的扩展属性名称
	 *            节点其他属性的数据名称,比如attributes.icon,菜单表中可以设置菜单图标
	 * @param treeNodeChildren
	 *            树节点下级节点集合的名称
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Map<String, Object>> getTreeData(List<?> data,
			String idFieldName, String textFieldName, String pidFieldName,
			Object parendIdValue, String treeNodeId, String treeNodeText,
			String treeNodeAttr,String treeNodeChildren) throws Exception {
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < data.size(); i++) {

			Object object = data.get(i);

			Object idValue = null;
			Object textValue = null;
			Object pidValue = null;
			List<Map<String, Object>> children = null;
			
			Map<String, Object> attributes = new HashMap<String, Object>();

			if (object instanceof Map) {// 如果是map
				Method method = Map.class.getMethod("get", String.class);
				idValue = method.invoke(object, idFieldName);
				textValue = method.invoke(object, textFieldName);
				pidValue = method.invoke(object, pidFieldName);
				//添加数据中的其他属性
				attributes.putAll((Map)object);
			} else {// 如果是实体类
				// 拿到id对应属性的值
				Field idField = object.getClass().getDeclaredField(idFieldName);
				if (!idField.isAccessible()) {
					idField.setAccessible(true);
				}
				idValue = idField.get(object);
				// 拿到text对应属性的值
				Field textField = object.getClass().getDeclaredField(
						textFieldName);
				if (!textField.isAccessible()) {
					textField.setAccessible(true);
				}
				textValue = textField.get(object);

				// 拿到pid对应属性的值
				Field pidField = object.getClass().getDeclaredField(
						pidFieldName);
				if (!pidField.isAccessible()) {
					pidField.setAccessible(true);
				}
				pidValue = pidField.get(object);
				
				//添加数据中的其他属性
				Field[] fields = object.getClass().getDeclaredFields();
				for (int j = 0; j < fields.length; j++) {
					Field field = fields[j];
					//如果是static或者是final修饰的字段,不添加到 其他属性的集合中
					if(Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers())){
						continue;
					}
					//设置属性的可见性
					if (!field.isAccessible()) {
						field.setAccessible(true);
					}
					String fieldName = field.getName();
					Object fieldValue = field.get(object);
					attributes.put(fieldName, fieldValue);
				}
			}
			// 判断当前节点的pid值是否是参数中的rootPidValue
			if (!parendIdValue.equals(pidValue)) {
				continue;
			}
			
			// 构造节点
			Map<String, Object> node = new HashMap<String, Object>();
			node.put(treeNodeId, idValue);//id
			node.put(treeNodeText, textValue);//text
			children = getTreeData(data, idFieldName, textFieldName,
					pidFieldName, idValue, treeNodeId, treeNodeText,
					treeNodeAttr,treeNodeChildren);
			node.put(treeNodeChildren, children);//children
			node.put(treeNodeAttr, attributes);//attributes
			res.add(node);
		}
		return res;
	}
	
	/**
	 * 从数据中得到前台页面easyui的combotree需要的数据对象
	 * @param data	原来的集合数据
	 * @param idFieldName	id对应的属性名称或者key名称
	 * @param textFieldName	text对应的属性名称或者key名称
	 * @param pidFieldName	pid对应的属性名称或者key名称
	 * @param parendIdValue	上级父节点的id值
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> getEasyuiTreeData(List<?> data,
			String idFieldName, String textFieldName, String pidFieldName,
			Object parendIdValue) throws Exception {
		return getTreeData(data, idFieldName, textFieldName, pidFieldName,
				parendIdValue, EASYUI_TREE_NODE_ID, EASYUI_TREE_NODE_TEXT,
				EASYUI_TREE_NODE_ATTR,EASYUI_TREE_NODE_CHILDREN);
	}
	
	/**
	 * easyui tree的节点数据属性名称
	 */
	private static String EASYUI_TREE_NODE_ID = "id";
	private static String EASYUI_TREE_NODE_TEXT = "text";
	private static String EASYUI_TREE_NODE_ATTR = "attributes";
	private static String EASYUI_TREE_NODE_CHILDREN = "children";
}