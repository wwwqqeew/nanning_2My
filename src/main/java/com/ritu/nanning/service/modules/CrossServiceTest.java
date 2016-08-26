package com.ritu.nanning.service.modules;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ritu.nanning.entity.Demo;
import com.ritu.nanning.entity.Track;
import com.ritu.nanning.service.account.RoleService;
import com.ritu.nanning.utils.o;
import com.ritu.nanning.utils.base.JunitServiceBase;

public class CrossServiceTest extends JunitServiceBase {

//	@Autowired
//	private RoleService roleService;// 业务层对象
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private TrackService trackService;

	@Autowired
	private DemoService demoService;
	public static String xlsFile = "d://test.xls"; // 产生的Excel文件的名称
	
	public int yc = 0;
	public int lb = 0;
	public int tttt = 0;
	public int isCf = 0;
	public int all = 0;
	public int save = 0;
//	HashMap<String,CommonTypeEntity> hashTest = new HashMap(); //数据库中获取的一级数据
	
	
	@Test
	public void test() throws Exception {
//		testPoiMy();
//		String na = "d:/test.xls";
//		System.out.println("开始了0000:"+file.getInputStream());
//		List<List<String>> list = testPoiU(na,null);
//		readExcelMain(list,"QU");
//		o.o(demoService.findAll());
//		Track Track = new Track();
//		Demo demo = new Demo();
//		demo.setId(1l);
//		Track.setDemo(demo);
//		Track.setAddress("ddddddd");
//		o.o(Track);
		//trackService.add(Track);
		//demoService.CreateTable();
	}

	public void checkIsCf(String str){
		if (str != null) {
			isCf ++;
		}
	}
//	/**
//	 * 获取的Excel内容和ID的助记码
//	 * @param list
//	 * @param IDzjm
//	 */
//	private void readExcelMain(List<List<String>> list ,String IDzjm) {
////		List<List<String>> list = testPoiU(xlsFile);
//		HashMap<String,List<CommonTypeEntity>> hashAllPId = new HashMap(); //所有Excel数据的,父类ID作为主键
//		HashMap<String,CommonTypeEntity> hashAllId = new HashMap(); //所有Excel数据的，自带的ID作为主键
//		HashMap<String,CommonTypeEntity> hashInDB = new HashMap(); //数据库中获取的一级数据
//		
//		List listFirstData = new ArrayList(){};//保存所有没有在hashAllId中找到数据的ID
//		boolean thisTimeIsSave = false;
//		CommonTypeEntity ctEntity= new CommonTypeEntity();
//		/**
//		 * 内容列数
//		 */
//		int useInt = 3;
//		int times = 0;
//		//所有数据放到HashMap
//		if (list != null) {
//			//获取行
//			for (int HangInt = 0; HangInt < list.size(); HangInt++) {
//				//获取列
////				for (int j = 0; j < list.get(j).size() && useInt >j; j++) {
//					ctEntity= new CommonTypeEntity();
//					ctEntity.setId("XZQ_"+list.get(HangInt).get(0));
//					ctEntity.setName(list.get(HangInt).get(1));
//					ctEntity.setParentId(list.get(HangInt).get(2));
//					//根据ID存放
//					hashAllId.put(list.get(HangInt).get(1), ctEntity);
//					hashTest.put(list.get(HangInt).get(1)+""+list.get(HangInt).get(2), ctEntity);
//					//根据父类ID放数据 
//					if (hashAllPId.get(list.get(HangInt).get(2)) != null) {
//						hashAllPId.get(list.get(HangInt).get(2)).add(ctEntity);
//						times ++;
//					} else {
//						//新建一个List放到hashAll里
//						List<CommonTypeEntity> newList = new  ArrayList<CommonTypeEntity>();
//						newList.add(ctEntity);
//						//父类ID作为主键
//						hashAllPId.put(list.get(HangInt).get(2), newList);
//						times ++;
//					}
////				}
//			}
//			
//		}else{
//			System.out.println("list:为空   "+list);
//		}
//		System.out.println("父类个数："+hashAllPId.size());
//		System.out.println("00000:前"+hashTest.size());
//		
//		Iterator iterAllPId = hashAllPId.entrySet().iterator(); 
//		Iterator iterAllId = hashAllId.entrySet().iterator(); 
//		
//		//检查，父类ID作为主键的 HashMap，是否存在其父类ID的hashAllId数据，不存在则为一级数据
//        while (iterAllPId.hasNext()) {  
//        	 Map.Entry<String, List<CommonTypeEntity>> entry = (Map.Entry<String, List<CommonTypeEntity>>) iterAllPId.next(); 
////              while (iterAllId.hasNext()) {
////            	  String keyAllId = (String) iterAllId.next();  
//                  if (hashAllId.get(entry.getKey()) == null) {
//                	  listFirstData.add(entry.getKey());
//				}
////			}
//        }  
//		
//        //循环查找各个一级的数据
//        for (int i = 0; i < listFirstData.size(); i++) {
//        	String pareIDDB = (String) listFirstData.get(i);
//        	//处理数据
//        	StartCLExcel(hashAllPId, hashInDB, pareIDDB,"0","第一级别:"+i);
//		}
//		lb = hashAllPId.size();
//		
////		Iterator iter = hashAll.entrySet().iterator();
////		while (iter.hasNext()) {
////			System.out.println((List<CommonTypeEntity>) iter.next());
////		}
//		
////		Iterator i = hashAllPId.entrySet().iterator();
////		while(i.hasNext()){
////		    Object o = i.next();
////		    String key = o.toString();
//////		    System.out.println(key);
////		   //这样就可以遍历该HashMap的key值了。
////		}
//		System.out.println("times:"+times);
//		System.out.println("数据库总数："+hashInDB.size());
//		System.out.println("剩余的个数："+hashAllPId.size());
//		System.out.println("总类："+lb);
//		System.out.println("移除次数："+yc);
//		System.out.println("tttt："+tttt);
//		if (list != null) {
//			System.out.println("list:"+list.size());
//		}else{
//			System.out.println("list:"+list);
//		}
//		
//		System.out.println("00000:后台"+hashTest.size());
//		System.out.println("isCf:"+isCf);
//		System.out.println("all:"+all);
//		System.out.println("save:"+save);
//	}
//
//
//	/**
//	 * 数据处理
//	 * @param hashAllPId Excel中获取的数据
//	 * @param hashInDB 数据库中获取的pareID数据
//	 * @param pareIDDB 数据库中获取的父类ID
//	 * @param pareID 父类ID
//	 */
//	private void StartCLExcel(HashMap<String, List<CommonTypeEntity>> hashAllPId,
//			HashMap hashInDB, String pareIDDB, String pareID, String pareName) {
//		//获取数据库中一级数据
//		List<CommonTypeEntity> listOne = cService.getListByParentId(pareIDDB);
//		tttt += listOne.size();
//		//获取一级数据
//		if (listOne != null) {
//			for (int i = 0; i < listOne.size(); i++) {
//				String str = (String) hashInDB.put(listOne.get(i).getName()+listOne.get(i).getParentId(), listOne.get(i));
//				checkIsCf(str);
//			}
//		}else {
//			System.out.println("000000000000000000:"+pareName);
//		}
//		
//		int findOne = 0;
//		//检查并且本父类ID中添加没有的数据
//		for (int i = 0; i < hashAllPId.get(pareID).size() ; i++) {
//			boolean isFind = false;
//			//数据是否已经存在hashInDB
//			if (hashInDB.get(hashAllPId.get(pareID).get(i).getName()+hashAllPId.get(pareID).get(i).getParentId()) != null) {
//				findOne ++;
//				System.out.println("["+hashAllPId.get(pareID).get(i).getName()+hashAllPId.get(pareID).get(i).getParentId()+"][找到]");
//				
//			} else {
////				System.out.println("["+hashAll.get(pareID).get(i).getName()+"][没有]");
//				//数据保存
////				System.out.println("开始保存:"+(hashAll.get(pareID).get(i)));
//				CommonTypeEntity cte = new CommonTypeEntity();
//				cte.setName(hashAllPId.get(pareID).get(i).getName());
//				cte.setParentId(pareIDDB);
//				cte.setId(hashAllPId.get(pareID).get(i).getId());
//				cte.setEtpId("0434ac46-fe09-4f5e-80b5-d5bd4c3e4b1c");
//				cte = cService.addHaveId(cte);
////				cte =
//				String str = (String)hashInDB.put(hashAllPId.get(pareID).get(i).getName()+hashAllPId.get(pareID).get(i).getParentId(), cte);
//				checkIsCf(str);
//				save ++;
////				i--;
////				System.out.println("["+hashAll.get(pareID).get(i).getName()+"]"+cte);
//				//saveCTEntity(hashAll, hashInDB, listOne, hashAll.get(pareID).get(i).getId(), i);
//			}
//		}
//		//检查下一级的数据
//		for (int i = 0; i < hashAllPId.get(pareID).size() ; i++) {
//			//开始进入下一级处理
//			if (hashAllPId.get(hashAllPId.get(pareID).get(i).getId()) != null) {
////				StartCLExcel(hashAllPId, 
////						hashInDB,
////						((CommonTypeEntity)hashInDB.get(hashAllPId.get(pareID).get(i).getName()+hashAllPId.get(pareID).get(i).getParentId())).getId(),
////						hashAllPId.get(pareID).get(i).getId(), 
////						hashAllPId.get(pareID).get(i).getName());
//			} else {
//				
////				System.out.println("["+hashAll.get(pareID).get(i).getName()+"]已经是最后一级");
////				hashAll.get(pareID).remove(i);
//			}
//			all ++;
//			hashTest.remove(hashAllPId.get(pareID).get(i).getName()+""+pareID);
//		}
//		hashAllPId.remove(pareID);
//		yc ++;
////		System.out.println("["+pareName+"]个数："+hashInDB.size() +"  找到："+findOne +"  未找到："+(hashInDB.size() - findOne)+"\n");
//	}
//
//	/**
//	 * 数据保存
//	 * @param hashAll 
//	 * @param hashInDB
//	 * @param listOne
//	 * @param i
//	 */
//	private void saveCTEntity(HashMap<String, 
//			List<CommonTypeEntity>> hashAll,
//			HashMap hashInDB, 
//			List<CommonTypeEntity> listOne, 
//			String pareID,
//			int i) {
//		System.out.println("开始保存:"+(hashAll.get(pareID).get(i)));
//		cService.add(listOne.get(i));
//		CommonTypeEntity cte = listOne.get(i);
//		hashInDB.put(hashAll.get(pareID).get(i).getName(), cte);
//		System.out.println("["+hashAll.get(pareID).get(i).getName()+"]"+cte);
//	}
//
//	
//	/**
//	 * 根据路径获取Excel的List
//	 * @return
//	 */
//	private List<List<String>> testPoiU(String fileName,InputStream s) {
//		ReadExcel poi = new ReadExcel();
//
//		// List<List<String>> list = poi.read("d:/aaa.xls");
//
//		List<List<String>> list = poi.read(fileName);
//
//		return list;
//	}

	
}

	