package com.ritu.nanning.utils;

/**
 * 信号系统（863）通信协议
 * 	常量
 * @author XL
 */
public interface UTCSNetProtocol {
	
	public static final byte INVALID_BYTE 	= (byte)255; // 
	public static final byte INVALID_ID 	= (byte)255; // 
	

	public static final byte END_CHAR_NUM=(byte)1;		//分帧字符数(帧头、尾都有)
	public static final byte VERSION_2_0=0x20;			//通讯协议版本号(高位主版本号，低位副版本号)
	public static final byte VERSION_LEN=1;				//通讯协议字节数
	public static final byte CHECKCODE_LEN=1;			//校验码字节数
	
	
	//in table content, ESC_CHAR+ESC_END = END_CHAR; ESC_CHAR+ESC_ESC = ESC_CHAR.
	public static final byte END_CHAR = (byte) (0xC0); // 192
	public static final byte ESC_CHAR = (byte) (0xDB); // 219
	public static final byte ESC_END  = (byte) (0xDC); // 220
	public static final byte ESC_ESC  = (byte) (0xDD); // 221
	
	//Version+SrcID+DestID+Datalink+ExtOneByte+RegionNo+CrossNo+Reserced[2]
	public static final byte PKG_HEAD_LEN=(byte)9;		//数据包头的长度		 	
	
	/**
	 * 未打包数据最小长度
	 */
	public static final int MIN_PKGSIZE=PKG_HEAD_LEN;
	/**
	 * 未打包数据最大长度
	 */
	public static final int MAX_PKGSIZE=480;
	/**
	 * 已打包数据最小长度
	 */
	public static final int MIN_PACKEDPKGSIZE=END_CHAR_NUM + MIN_PKGSIZE + CHECKCODE_LEN + END_CHAR_NUM;
	/**
	 * 已打包数据最大长度
	 */
	public static final int MAX_PACKEDPKGSIZE=END_CHAR_NUM + MAX_PKGSIZE*2 + CHECKCODE_LEN + END_CHAR_NUM;
	
	/**
	 * 消息域的最小长度
	 */
	public static final int MIN_PKGMSGAREASIZE=2;
	/**
	 * 消息域的最大长度
	 */
	public static final int MAX_PKGMSGAREASIZE=(MAX_PKGSIZE - PKG_HEAD_LEN);
	
	//数据包中各个字节的偏移量
	public static final int MSG_OFFSET_VERSION=0;
	public static final int MSG_OFFSET_SOURCEID=1;
	public static final int MSG_OFFSET_DESTID=2;
	public static final int MSG_OFFSET_DATALINKCODE=3;
	public static final int MSG_OFFSET_COMMANDCODE=4;
	public static final int MSG_OFFSET_REGIONNO=5;
	public static final int MSG_OFFSET_CROSSNO=6;
	public static final int MSG_OFFSET_BELONGTOCROSSNO=7;
	public static final int MSG_OFFSET_RESERVE=8;
	public static final int MSG_OFFSET_MSGTYPE=9;
	public static final int MSG_OFFSET_MSGAREASTART=10;

	public static final int MAX_IPV4ADDRESS_LEN=16;

//	public static final int DEFAULT_CENTER_LISTEN_POR=3005;	//默认中心通讯服务监听端口号：3005
	
	//define opertype in communication
	public static final byte MSG_OPERTYPE_HEAD					=(byte)0x80;
	public static final byte MSG_OPERTYPE_MAXOBJNUM				=8;
		
	public static final byte MSG_OPERTYPE_QUERY					=0x00;
	public static final byte MSG_OPERTYPE_SETUP					=0x01;
	public static final byte MSG_OPERTYPE_SETUP_NOREPLY			=0x02;
	public static final byte MSG_OPERTYPE_PUSH					=0x03;
	public static final byte MSG_OPERTYPE_QUERY_REPLY			=0x04;
	public static final byte MSG_OPERTYPE_SETUP_REPLY			=0x05;
	public static final byte MSG_OPERTYPE_ERROR_REPORT			=0x06;

	// public static final byte MSG_OPERTYPE_UPCOMMUTE				0x08
	// public static final byte MSG_OPERTYPE_UPCOMMUTE_NEEDREPLY	0x09
	public static final byte MSG_OPERTYPE_QUERYWITHPARAM		=0x0A;
	public static final byte MSG_OPERTYPE_QUERYWITHPARAM_REPLY	=0x0B;


	
	public static final byte MSG_OBJ_LaneFlow_5Min			=(byte)0x47;		//5分钟流量统计数据
	//define object in communication
	public static final byte MSG_OBJ_UserClientConnState	=(byte)0x51;
	public static final byte MSG_OBJ_UserClientConnInfoTab	=(byte)0x52;
	public static final byte MSG_OBJ_UserClientLoginInfoTab	=(byte)0x53;

	public static final byte MSG_OBJ_UserAccountTolNum		=(byte)0x61;
	public static final byte MSG_OBJ_UserAccountAddTab		=(byte)0x62;
	public static final byte MSG_OBJ_UserAccountTab			=(byte)0x63;
	public static final byte MSG_OBJ_UserAccountDelTab		=(byte)0x64;
	public static final byte MSG_OBJ_UserAccountModiTab		=(byte)0x65;


	public static final byte MSG_OBJ_RegionCommServConnState	=(byte)0x6A;
	public static final byte MSG_OBJ_SysParamServConnState		=(byte)0x71;
	public static final byte MSG_OBJ_CrossPersServConnState		=(byte)0x7A;

	public static final byte MSG_OBJ_RegionCommServConnInfoTab	=(byte)0x6B;
	public static final byte MSG_OBJ_TSCConnInfoTab				=(byte)0x6E;
	public static final byte MSG_OBJ_SysParamServConnInfoTab	=(byte)0x72;
	public static final byte MSG_OBJ_CrossPersServConnInfoTab	=(byte)0x7B;

	public static final byte MSG_OBJ_DataStatServLoginInfoTab	=(byte)0x43;
	public static final byte MSG_OBJ_RegionCommServLoginInfoTab	=(byte)0x69;
	public static final byte MSG_OBJ_SysParamServLoginInfoTab	=(byte)0x73;
	public static final byte MSG_OBJ_CrossPersServLoginInfoTab	=(byte)0x7C;

	public static final byte MSG_OBJ_CenterActiveConnNum		=(byte)0x5A;
	public static final byte MSG_OBJ_CenterConnLimit			=(byte)0x5B;
	public static final byte MSG_OBJ_CenterFlowCount			=(byte)0x5D;

	public static final byte MSG_OBJ_TSCFaultStatus				=(byte)(0xD1);
	public static final byte MSG_OBJ_TSCCtrlMode				=(byte)(0xD2);
	public static final byte MSG_OBJ_TSCCtrlPlanStage			=(byte)(0xD3);
	public static final byte MSG_OBJ_TSCInterStage				=(byte)(0xD4);
	public static final byte MSG_OBJ_TSCCycle					=(byte)(0xD5);
	
	public static final byte MSG_OBJ_AssignCtrlMode				=(byte)(0xDA);
	public static final byte MSG_OBJ_AssignPhase				=(byte)(0xDB);
	public static final byte MSG_OBJ_StageStep					=(byte)(0xDC);
	
	public static final byte MSG_OBJ_SysCtrlPlan				=(byte)(0xF4);
	
	public static final byte SysParamSet_ID 	=(byte)104;
	public static final byte CrossPersSet_ID 	=(byte)103;
	public static final byte Centert_ID 		=(byte)101;
	public static final byte REGION_ID 			=(byte)102;
	public static final byte TSC_ID 			=(byte)120;
	public static final byte DataStatServ_ID 	=(byte)108;
	 
	public static final byte BROADCAST_ID 			=(byte)255;
	
	/**
	 * 控制方式
	 */
	//区域协调 = 1，上位机执行区域自适应协调算法
	public static final byte CONTROL_MODE_REGION_CORRESPOND	=(byte)1;
	//干线协调 = 2，上位机执行干线控制协调算法
	public static final byte CONTROL_MODE_LINE_CORRESPOND	=(byte)2;
	//远程手动
	public static final byte CONTROL_MODE_REMOTE_MANUAL	=(byte)7;
	//指定相位
	public static final byte CONTROL_MODE_ASSIGN_STAGE	=(byte)8;
	
}
