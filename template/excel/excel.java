import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ritu.marketing.common.util.DateUtil;
import com.ritu.marketing.dao.model.Mirror;
import com.ritu.marketing.service.impl.MirrorServiceImpl.sum;
import com.ritu.trafficlight.utils.excel.ImportMsg;

<#include "/macro.include"/>
<#include "/java_copyright.include">
<#include "/judgeProperty.include">
<#include "/java_util.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>

/**
 * 导入Excel
 * @param list
 */
@Transactional(readOnly = false)
public ImportMsg importExcel(List<List<String>> list) {
	
//	List<List<String>> list = testPoiU("d:\\新文件名.xls");
//	System.out.println("数据条数："+list.size());
//	for (int i = 0; i < list.size(); i++) {
//		System.out.print(i +" ");
//		for (int j = 0; j < list.get(i).size(); j++) {
//			System.out.print("["+j+"]" + list.get(i).get(j)+"  ");
//		}
//		System.out.println(" ");
//	}
	
	ImportMsg msg = new ImportMsg();
	msg.allDataInt = list.size()-1;
	StringBuffer MSG = new StringBuffer();

	for (int i = 1; i < list.size(); i++) {
	//	for (int j = 0; j < list.get(i).size(); j++) {
			//System.out.print("["+j+"] " + list.get(i).get(j));
			
			int statrVal = 1;//读取数据的开始行数，第一列为列序号，第二为编号，数据第三开始
			${className} ${classNameLower} = new ${className}();
			//Excel列表检测
			<#list table.columns as column>
			<#if column.isDateTimeColumn>
			//时间类型检测
			//${htm_notes(column)}
			String data${column.columnName} = list.get(i).get(statrVal++).trim();//
			try {
				${classNameLower}.set${column.columnName}(DateUtil.StringToDate(data${column.columnName}));//日期
			} catch (Exception e) {
				msg.FaltSaveInt++;
				MSG.append("第"+(i+1)+"行，第"+(statrVal)+"列，日期格式不正确<br>\n");
				continue;
			}
			<#elseif column.pk>
			//主键
			<#elseif !column.null>
			//${htm_notes(column)}
			String data${column.columnName} = list.get(i).get(statrVal++).trim();
			try {
				${classNameLower}.set${column.columnName}(data${column.columnName});
			} catch (Exception e) {
				msg.FaltSaveInt++;
				MSG.append("第"+(i+1)+"行，第"+(statrVal)+"列，${htm_notes(column)}不正确能为空<br>\n");
				continue;
			}
			<#else>
			//${htm_notes(column)}
			${classNameLower}.set${column.columnName}(list.get(i).get(statrVal++).trim());
			
			<#--</#if>-->
			</#if>
			</#list>
			try {
				add(${classNameLower});
				msg.successSaveInt++;
				System.out.println("保存成功："+${classNameLower});
			} catch (Exception e) {
				msg.FaltSaveInt++;
				System.out.println("信息添加失败:"+${classNameLower});
			}
		}
	msg.setMsg(MSG.toString());
	System.out.println(MSG);
	return msg;

}