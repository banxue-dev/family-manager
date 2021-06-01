package com.family.utils;
/**
 *Good Luck
 *
*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * 数据库表转换成javaBean对象小工具, 要求：数据库表名为xx_xx，转换后的bean名称为XxXx;
 * bean属性按原始数据库字段经过去掉下划线,并大写处理首字母等等.
 */
public class BeanUtil {
	/**
	 * controller上的额外参数比如用户id呀这种
	 */
//	private String expireParam=" ,@RequestHeader(\"platformType\") @ApiIgnore PlatformTypeEnum platformType, @RequestHeader(\"user\") @ApiIgnore String userName,@RequestHeader(\"userId\") @ApiIgnore Integer userId,@RequestHeader(\"merchantShopId\") @ApiIgnore Integer merchantShopId";//额外参数
	private String expireParam="";//额外参数
	/**
	 * controller上的额外的名称前缀
	 */
	private String expirePlatName="";
	private String auther="feng";//作者名称
	private String EntityName = "";// 实体名
	private String EntityVOName = "";// 实体VO名
	private String EntityDOName = "";// 实体DO名
	private String MinEntityName = "";// 首字母小写后的实体名
	private String SoureEntityName = "";// 数据库中的表名
	private String SoureTableComment = "";// 表的注释
//	private String ControllerIsapi = "/api";
	private String ControllerVersion = "/v1.0";
	private String[] colnames;// 列名--转化后的驼峰
	private String[] colSourceNames; // 列名在数据库中的名字
	private String[] colTypes; // 列的java类型
	private String[] colSourceTypes; // 列的原类型
	private String[] colremarks; // 列的注释
	private int[] colSizes; // 列名大小
	private int[] colScale; // 列名小数精度
	private boolean importUtil = false;
	private boolean importSql = false;
	private boolean importMath = false;
	private String ServerName = "www.banxue.fun:3306";// localhost:3306IP和端口号
//	private String BaseName = "shop_service_user";// 数据库名称
	private String BaseName = "family";// 数据库名称
//	private String BaseName = "shop_service_market";// 数据库名称
	private String UserName = "root";// 用户名
	private String PassWord = "feng.123";// 密码
	private String rootPath = "D://JavaBean/";
	// 包名
	private String packageName = "com.family.normal.";
	private String utilpackageName = "com.family.utils.";
	private String EntityPackge = packageName + "entity";
	private String EntityVOPackge = EntityPackge + ".VO";
	private String EntityDOPackge = EntityPackge + ".DO";
	private String SvervicePackge = packageName + "service";
	private String ControllerPackge = packageName + "controller";
	private String DaoPackge = packageName + "mapper";
	// private String ToolPackge=packageName+"Tool";
	Boolean isSingle = true;// 是否指定表来生成
	private String Pre_fix = "normal_";// 要去掉的表名前缀
	String SingleTableName = "normal_file_system";// 上面isSingle=true的话，就在这指定你要生成的表
//	String SingleTableName = "wb_banner_info,wb_case_info,wb_contact_info,wb_group_config,wb_leaving_message_info,wb_navigation_menu_info,wb_news_info,wb_service_range_info,wb_friendship_link_info";// 上面isSingle=true的话，就在这指定你要生成的表
	public static Connection conn = null;
	ResultSetMetaData rsmd = null;
	DatabaseMetaData dbmd = null;
	ResultSet rs = null;

	/**
	 * 构造加载conn链接 Author:FENG
	 */
	BeanUtil() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + ServerName + "/" + BaseName + "?" + "user=" + UserName
					+ "&password=" + PassWord + "&useUnicode=true&characterEncoding=UTF8");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取metaData数据集 Author:FENG 2016年5月11日
	 *
	 * @param sql
	 * @return
	 */
	public ResultSetMetaData getRs(String sql) {
		// 获取连接
		try {
			// 实现sql
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeQuery();
			ResultSetMetaData rsmd = pstmt.getMetaData();
			return rsmd;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取rsultSet数据集 Author:FENG 2016年5月11日
	 *
	 * @param sql
	 * @return
	 */
	public static ResultSet getRset(String sql) {
		// 获取连接
		try {
			// 实现sql
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *            Author:FENG
	 * @throws ClassNotFoundException
	 */
	public void tableToEntity() throws ClassNotFoundException {
		// 加载驱动
		Class.forName("com.mysql.cj.jdbc.Driver");

		try {

			// 获取数据库表名集合的sql
			String te = "SELECT TABLE_NAME,TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='" + BaseName
					+ "'";

			// 实现sql
			rs = getRset(te);
			// if(!rs.next())return;
			// 查看共有多少张表
			String names[]=SingleTableName.split(",");
			while (rs.next()) {
				// 获取表名
				SoureEntityName = rs.getString(1);
				SoureTableComment = rs.getString(2);
				// 指定生成的表名规则
				// 指定单个表
				if (isSingle) {

					boolean s=false;
					for(String tn:names) {
						if (SoureEntityName.equals(tn)) {
							s=true;
						}
					}
					if (!s) {
						continue;
					}
				}
				dbmd = conn.getMetaData();
				ResultSet resultSet = dbmd.getTables(null, "%", SoureEntityName, new String[] { "TABLE" });
				EntityName = this.initcap(this.prefix(SoureEntityName));
				EntityVOName = EntityName + "VO";
				EntityDOName = EntityName + "DO";
				MinEntityName = this.initMin(EntityName);
				// System.out.println("表名："+EntityName+"--"+SoureTableComment);
				String strsql = "SELECT * FROM " + SoureEntityName + " limit 0,1";// +" WHERE ROWNUM=1" 读一行记录;
				// 获取此表的列名
				rsmd = getRs(strsql);
				int size = rsmd.getColumnCount();
				colnames = new String[size];
				colTypes = new String[size];
				colSourceTypes = new String[size];
				colSourceNames = new String[size];
				colSizes = new int[size];
				colScale = new int[size];
				colremarks = new String[size];
				while (resultSet.next()) {
					String tableName = resultSet.getString("TABLE_NAME");
					if (tableName.equals(SoureEntityName)) {
						DatabaseMetaData  tmpd=conn.getMetaData();
//						dbmd.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern)
//						ResultSet rs = tmpd.getColumns(null, tmpd.getUserName(), tableName.toUpperCase(),"%");
						ResultSet rs = tmpd.getColumns(null,getSchema(conn), tableName,"%");
						int i = 0;
						while (rs.next()) {
							// System.out.println("字段名："+rs.getString("COLUMN_NAME")+"--字段注释："+rs.getString("REMARKS")+"--字段数据类型："+rs.getString("TYPE_NAME"));
							String colName = rs.getString("COLUMN_NAME");
							String remarks = rs.getString("REMARKS");
							String dbType = rs.getString("TYPE_NAME");
							colnames[i] = colName;
							colSourceNames[i] = colName;
							colTypes[i] = dbType.toLowerCase();
							colSourceTypes[i]=dbType.toLowerCase();
							colScale[i] = rsmd.getScale(i + 1);
							colremarks[i] = remarks;
							if ("datetime".equals(colTypes[i])) {
								importUtil = true;
							}
							if ("image".equals(colTypes[i]) || "text".equals(colTypes[i])) {
								importSql = true;
							}
							if (colScale[i] > 0) {
								importMath = true;
							}
							colSizes[i] = rsmd.getPrecision(i + 1);
							colTypes[i] = DataBaseDataType(colTypes[i], colScale[i], colSizes[i]);

							i++;
						}
					}
				}
				String content = parse(colnames, colTypes, colSizes, "entity");
				String entityVo = parse(colnames, colTypes, colSizes, "VO");
				String entityDo = parse(colnames, colTypes, colSizes, "DO");
				String entityAd = parse(colnames, colTypes, colSizes, "AD");
				String dao = parseDao();
				String service = parseIService();
				String controller = parseController();
				String ServiceImpl = parseServiceImpl();
				String mapper = parseMapper();
				try {
					String DaoPath = rootPath + "mapper";
					String IServicePath = rootPath + "service";
					String ImplPath = IServicePath + "/Impl";
					String ControllerPath = rootPath + "controller";
					String EntityPath = rootPath + "entity";
					String EntityVOPath = EntityPath + "/VO";
					String EntityDOPath = EntityPath + "/DO";
					String MapperPath = rootPath + "MappingConfig";
					File file = new File(rootPath);
					file.mkdir();
					file = new File(DaoPath);
					file.mkdir();
					file = new File(IServicePath);
					file.mkdir();
					file = new File(ImplPath);
					file.mkdir();
					file = new File(ControllerPath);
					file.mkdir();
					file = new File(EntityPath);
					file.mkdir();
					file = new File(EntityDOPath);
					file.mkdir();
					file = new File(EntityVOPath);
					file.mkdir();
					file = new File(MapperPath);
					file.mkdir();
					FileWriter fw = new FileWriter(EntityPath + "/" + EntityName + ".java");
					PrintWriter pw = new PrintWriter(fw);
					pw.println(content);
					pw.flush();
					fw = new FileWriter(EntityVOPath + "/" + EntityName + "VO.java");
					pw = new PrintWriter(fw);
					pw.println(entityVo);
					pw.flush();
					fw = new FileWriter(EntityDOPath + "/" + EntityName + "DO.java");
					pw = new PrintWriter(fw);
					pw.println(entityDo);
					pw.flush();
					fw = new FileWriter(EntityDOPath + "/" + EntityName + "AD.java");
					pw = new PrintWriter(fw);
					pw.println(entityAd);
					pw.flush();
					fw = new FileWriter(IServicePath + "/" + "I" + EntityName + "Service" + ".java");
					pw = new PrintWriter(fw);
					pw.println(service);
					pw.flush();
					fw = new FileWriter(DaoPath + "/" + EntityName + "Mapper" + ".java");
					pw = new PrintWriter(fw);
					pw.println(dao);
					pw.flush();
					fw = new FileWriter(ControllerPath + "/" + EntityName + "Controller" + ".java");
					pw = new PrintWriter(fw);
					pw.println(controller);
					pw.flush();
					fw = new FileWriter(ImplPath + "/" + EntityName + "ServiceImpl" + ".java");
					pw = new PrintWriter(fw);
					pw.println(ServiceImpl);
					pw.flush();
					fw = new FileWriter(MapperPath + "/" + EntityName + "Mapper" + ".xml");
					pw = new PrintWriter(fw);
					pw.println(mapper);
					pw.flush();
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 生成dao文件 Author:FENG 2016年5月11日
	 * @return
	 */
	private String parseDao() {
		StringBuffer sb = new StringBuffer();
		sb.append("package " + DaoPackge + "; \r\n");
		sb.append("import " + EntityPackge + "." + EntityName + "; \r\n");
		sb.append("import tk.mybatis.mapper.common.Mapper; \r\n");
		sb.append("/** \r\n");
		sb.append("* " + EntityName + "数据层 \r\n");
		sb.append("* Auther:"+auther+"\r\n");
		sb.append("* Date:" + TimeUtils.getCurrentTime() + "\r\n");
		sb.append("*/ \r\n");
		sb.append("@org.apache.ibatis.annotations.Mapper \r\n");
		sb.append("public interface " + EntityName + "Mapper extends Mapper<" + EntityName + "> {  \r\n\n");
		sb.append("}");
		return sb.toString();
	}

	private String parseIService() {
		StringBuffer sb = new StringBuffer();
		sb.append("package " + SvervicePackge + "; \r\n");
		sb.append("import " + EntityPackge + "." + EntityName + "; \r\n");
		sb.append("import com.github.pagehelper.PageHelper; \r\n");
		sb.append("import com.github.pagehelper.PageInfo; \r\n");
		sb.append("import "+utilpackageName+"LayuiPage; \r\n");
		sb.append("import "+utilpackageName+"ResultObject; \r\n");
		sb.append("import "+EntityVOPackge+"."+EntityVOName+"; \r\n");
    	sb.append("import "+EntityDOPackge+"."+EntityDOName+"; \r\n");
		sb.append("import java.util.List;\r\n");
		sb.append("/** \r\n");
		sb.append("* " + EntityName + "服务接口层 \r\n");
		sb.append("* Auther:"+auther+"\r\n");
		sb.append("*/ \r\n");
		sb.append("public interface I" + EntityName + "Service  {  \r\n\n");
		sb.append("\t/** \r\n");
		sb.append("\t* 获取单页记录 \r\n");
		sb.append("\t* Auther:"+auther+"\r\n");
		sb.append("\t*/ \r\n");
		sb.append("\tpublic " + EntityVOName + " getSingleInfo(" + EntityDOName + " " + MinEntityName + "DO);\r\n");
		sb.append("\t/** \r\n");
		sb.append("\t* 根据id获取数据 \r\n");
		sb.append("\t* Auther:"+auther+"\r\n");
		sb.append("\t*/ \r\n");
		sb.append("\t" + EntityVOName + " getSingleInfoById(" + colTypes[0] + " " + colnames[0] + ");\r\n");
		sb.append("\t/** \r\n");
		sb.append("\t* 获取列表记录 \r\n");
		sb.append("\t* Auther:"+auther+"\r\n");
		sb.append("\t*/ \r\n");
		sb.append("\tpublic List<" + EntityVOName + "> get" + EntityName + "List(" + EntityDOName + " " + MinEntityName
				+ "DO);\r\n");
		sb.append("\t/** \r\n");
		sb.append("\t* 获取分页记录 \r\n");
		sb.append("\t* Auther:"+auther+"\r\n");
		sb.append("\t*/ \r\n");
		sb.append("\tpublic LayuiPage<" + EntityVOName + "> get" + EntityName + "ListByPage(" + EntityDOName + " "
				+ MinEntityName + "DO, LayuiPage<"+EntityVOName+"> layuiPage);\r\n");
		sb.append("\t/** \r\n");
		sb.append("\t* 删除记录 \r\n");
		sb.append("\t* Auther:"+auther+"\r\n");
		sb.append("\t*/ \r\n");
		sb.append("\tpublic ResultObject del" + EntityName + "(" + EntityName + " " + MinEntityName + "); \r\n");
		sb.append("\t/** \r\n");
		sb.append("\t* 修改信息 \r\n");
		sb.append("\t* Auther:"+auther+"\r\n");
		sb.append("\t*/ \r\n");
		sb.append("\tpublic ResultObject mod" + EntityName + "(" + EntityName + " " + MinEntityName + ");\r\n");
		sb.append("\t/** \r\n");
		sb.append("\t* 添加信息 \r\n");
		sb.append("\t* Auther:"+auther+"\r\n");
		sb.append("\t*/ \r\n");
		sb.append("\tpublic ResultObject addNew" + EntityName + "(" + EntityName + " " + MinEntityName + ");\r\n");

		sb.append("}");
		return sb.toString();
	}

	private String parseServiceImpl() {
		StringBuffer sb = new StringBuffer();
		sb.append("package " + SvervicePackge + ".Impl; \r\n");
		sb.append("import " + EntityPackge + "." + EntityName + "; \r\n");
		sb.append("import " + DaoPackge + "." + EntityName + "Mapper; \r\n");
		sb.append("import " + SvervicePackge + ".I" + EntityName + "Service; \r\n");
		sb.append("import org.springframework.stereotype.Service; \r\n");
		sb.append("import "+utilpackageName+"EntityChangeRquestView; \r\n");
    	sb.append("import "+EntityVOPackge+"."+EntityVOName+"; \r\n");
    	sb.append("import "+EntityDOPackge+"."+EntityDOName+"; \r\n");
		sb.append("import com.github.pagehelper.PageHelper; \r\n");
		sb.append("import "+utilpackageName+"ResultUtil; \r\n");
		sb.append("import "+utilpackageName+"ResultObject; \r\n");
		sb.append("import javax.persistence.Transient; \r\n");
		sb.append("import org.springframework.transaction.annotation.Transactional; \r\n");
		sb.append("import tk.mybatis.mapper.entity.Example; \r\n");
		sb.append("import java.util.ArrayList; \r\n");
		sb.append("import "+utilpackageName+"TimeUtils; \r\n");
		sb.append("import com.github.pagehelper.PageInfo; \r\n");
		sb.append("import "+utilpackageName+"LayuiPage; \r\n");
		sb.append("import "+utilpackageName+"StringUtils;\r\n");
		sb.append("import org.springframework.beans.factory.annotation.Autowired; \r\n");
		sb.append("import java.util.Map; \r\n");
		sb.append("import java.util.List; \r\n");
		sb.append("/** \r\n");
		sb.append("* " + EntityName + "服务层 \r\n");
		sb.append("* Auther:"+auther+"\r\n");
		sb.append("* Date:" + TimeUtils.getCurrentTime() + "\r\n");
		sb.append("*/ \r\n");
		sb.append("@Service \n");
		sb.append("public class " + EntityName + "ServiceImpl implements I" + EntityName + "Service { \r\n\n");
		sb.append("\t@Autowired\r\n");
		sb.append("\tprivate " + EntityName + "Mapper i" + EntityName + "Mapper;\r\n\n\r");
		sb.append("\t/** \r\n");
		sb.append("\t* 获取单页记录 \r\n");
		sb.append("\t* Auther:"+auther+"\r\n");
		sb.append("\t*/ \r\n");
		sb.append("\t@Override\r\n");
		sb.append("\tpublic " + EntityVOName + " getSingleInfo(" + EntityDOName + " " + MinEntityName + "DO) {\r\n");
		sb.append("\t\t"+EntityName+" "+MinEntityName+"=new "+EntityName+"();\r\n");
		sb.append("\t\t"+MinEntityName+"= i" + EntityName + "Mapper.selectOne(EntityChangeRquestView.createDOToEntity("+MinEntityName+"DO,new " + EntityName + "()));\r\n");
		sb.append("\t\treturn this.structDetailData("+MinEntityName+");\r\n");
		sb.append("\t}\r\n");
		sb.append("\t/** \r\n");
		sb.append("\t* 依据ID获取单页记录 \r\n");
		sb.append("\t* Auther:"+auther+"\r\n");
		sb.append("\t*/ \r\n");
		sb.append("\t@Override\r\n");
		sb.append("\tpublic " + EntityVOName + " getSingleInfoById(" + colTypes[0] + " " + colnames[0] + ") {\r\n");
		sb.append("\t\t"+EntityName+" "+MinEntityName+"=new "+EntityName+"();\r\n");
		sb.append("\t\t"+MinEntityName+"= i" + EntityName + "Mapper.selectByPrimaryKey(" + colnames[0] + ");\r\n");
		sb.append("\t\treturn this.structDetailData("+MinEntityName+");\r\n");
		sb.append("\t}\r\n");
		sb.append("\t/** \r\n");
		sb.append("\t* 获取列表记录 \r\n");
		sb.append("\t* Auther:"+auther+"\r\n");
		sb.append("\t*/ \r\n");
		sb.append("\t@Override\r\n");
		sb.append("\tpublic List<" + EntityVOName + "> get" + EntityName + "List(" + EntityDOName + " " + MinEntityName
				+ "DO) {\r\n");
		sb.append("\t\t  Example example = getPublicExample("+MinEntityName+"DO);\n");
		sb.append("\t\t  List<" + EntityVOName +"> lstVO = new ArrayList<" + EntityVOName +">();\r\n");
		sb.append("\t\t  List<" + EntityName + "> lst = i" + EntityName + "Mapper.selectByExample(example); \r\n");
		sb.append("\t\tlst.forEach(t->{\r\n");
		sb.append("\t\t  "+EntityVOName+" vo=this.structDetailData(t);\n");
		sb.append("\t\t  if(vo!=null) {\n");
		sb.append("\t\t  	lstVO.add(vo);\n");
		sb.append("\t\t  }\n");
		sb.append("\t\t});\n");
		sb.append("\treturn lstVO;\r\n");
		sb.append("\t} \r\n");
		sb.append("\t/** \r\n");
		sb.append("\t* 获取分页记录 \r\n");
		sb.append("\t* Auther:"+auther+"\r\n");
		sb.append("\t*/ \r\n");
		sb.append("\t@Override\r\n");
		sb.append("\t@Transactional\r\n");
		sb.append("\tpublic LayuiPage<" + EntityVOName + "> get" + EntityName + "ListByPage(" + EntityDOName + " "
				+ MinEntityName + "DO, LayuiPage<"+EntityVOName+"> layuiPage){\r\n");
		/*
		 * sb.append("\t\t  if (StringUtils.isNull(sort)) { \r\n");
		 * sb.append("\t\t  sort = \"create_time\"; \r\n");
		 * sb.append("\t\t }else { \r\n");
		 * sb.append("\t\t  sort=StringUtils.humpToLine2(sort);} \r\n");
		 * sb.append("\t\t  if (StringUtils.isNull(dire)) { \r\n");
		 * sb.append("\t\t  dire = \"desc\";} \r\n");
		 */
		sb.append("\t\t  Example example = getPublicExample("+MinEntityName+"DO);\r\n");
		sb.append("\t\t if(StringUtils.isNotNull(layuiPage.getSort())) {");

		sb.append("\t\t 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + \" \" + layuiPage.getDire());");
		sb.append("\t\t }else {");
			
		sb.append("\t\t 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());");
		sb.append("\t\t }");
		//sb.append("\t\t  PageHelper.startPage(pageNum, pageSize).setOrderBy(sort + \" \" + dire);\r\n");
		sb.append("\t\t  List<" + EntityVOName +"> lstVO = new ArrayList<" + EntityVOName +">();\r\n");
		sb.append("\t\t  List<" + EntityName + "> lst = i" + EntityName + "Mapper.selectByExample(example); \r\n");
		
		sb.append("\t\tPageInfo pageInfo=PageInfo.of(lst);\r\n");
		sb.append("\t\t  lst.forEach(t->{\r\n");
		sb.append("\t\t  "+EntityVOName+" vo=this.structDetailData(t);\r\n");
		sb.append("\t\t  if(vo!=null) {\r\n");
		sb.append("\t\t  	lstVO.add(vo);\r\n");
		sb.append("\t\t  }\n");
		sb.append("\t\t});\n");
		sb.append("\t\tpageInfo.setList(lstVO);\n");
		sb.append("\t\tlayuiPage = new LayuiPage<>(pageInfo);\n");

		sb.append("\t\t  return layuiPage; \r\n");
		sb.append("\t}\r\n");
		sb.append("\t/** \r\n");
		sb.append("\t* 删除记录 \r\n");
		sb.append("\t* Auther:"+auther+"\r\n");
		sb.append("\t*/ \r\n");
		sb.append("\t@Override\r\n");
		sb.append("\t@Transactional\r\n");
		sb.append("\tpublic ResultObject del" + EntityName + "(" + EntityName + " " + MinEntityName + ") {\r\n");
		
		sb.append("\t\t int i= i" + EntityName + "Mapper.updateByPrimaryKeySelective(" + MinEntityName + ");\r\n");
		sb.append("\t\tif(i<1) {\r\n");
		sb.append("\t\t	return ResultUtil.error(\"更新失败\");\r\n");
		sb.append("\t\t}\r\n");
		sb.append("\t\treturn ResultUtil.success(\"成功\");\r\n");
		sb.append("\t}\r\n");
		sb.append("\t/** \r\n");
		sb.append("\t* 修改信息 \r\n");
		sb.append("\t* Auther:"+auther+"\r\n");
		sb.append("\t*/ \r\n");
		sb.append("\t@Override\r\n");
		sb.append("\t@Transactional\r\n");
		sb.append("\tpublic ResultObject mod" + EntityName + "(" + EntityName + " " + MinEntityName + ") {\r\n");
		
		sb.append("\t\tint i= i" + EntityName + "Mapper.updateByPrimaryKeySelective(" + MinEntityName + ");\r\n");
		sb.append("\t\tif(i<1) {\r\n");
		sb.append("\t\t	return ResultUtil.error(\"更新失败\");\r\n");
		sb.append("\t\t}\r\n");
		sb.append("\t\treturn ResultUtil.success(\"成功\");\r\n");
		sb.append("\t}\r\n");
		sb.append("\t/** \r\n");
		sb.append("\t* 添加信息 \r\n");
		sb.append("\t* Auther:"+auther+"\r\n");
		sb.append("\t*/ \r\n");
		sb.append("\t@Override\r\n");
		sb.append("\t@Transactional\r\n");
		sb.append("\tpublic ResultObject addNew" + EntityName + "(" + EntityName + " " + MinEntityName + ") {\r\n");
		sb.append("\t\t"+MinEntityName+".setCreateTime(TimeUtils.getCurrentTime());\r\n");
		sb.append("\t\tint i= i" + EntityName + "Mapper.insertSelective(" + MinEntityName + ");\r\n");
		sb.append("\t\tif(i<1) {\r\n");
		sb.append("\t\t	return ResultUtil.error(\"更新失败\");\r\n");
		sb.append("\t\t}\r\n");
		sb.append("\t\treturn ResultUtil.success(\"成功\");\r\n");
		sb.append("\t}\r\n");

		sb.append("\t/** \r\n");
		sb.append("\t* 构造返回的数据 \r\n");
		sb.append("\t* Auther:"+auther+"\r\n");
		sb.append("\t*/ \r\n");
    	sb.append("\tprivate "+EntityVOName+" structDetailData("+EntityName+" "+MinEntityName+") { \r\n");
    	sb.append("\t\t if("+MinEntityName+"==null){ \r\n");
    	sb.append("\t\t\t return null; \r\n");
    	sb.append("\t\t} \r\n");
    	sb.append("\t\t"+EntityVOName+" vo= EntityChangeRquestView.createEntityToVO("+MinEntityName+",new "+EntityVOName+"()); \r\n");
    	sb.append("\t\treturn vo; \r\n");
    	sb.append("\t}\n");

    	sb.append("\t/** \r\n");
		sb.append("\t* 构造请求的条件 \r\n");
		sb.append("\t* Auther:"+auther+"\r\n");
		sb.append("\t*/ \r\n");
    	sb.append("\tprivate Example getPublicExample("+EntityDOName+" "+MinEntityName+"DO) { \r\n");
    	sb.append("\t\tExample example = new Example("+EntityName+".class); \r\n");
    	sb.append("\t\tExample.Criteria criteria = example.createCriteria(); \r\n");
    	sb.append("\t\tcriteria.andEqualTo(EntityChangeRquestView.createDOToEntity("+MinEntityName+"DO,new "+EntityName+"())); \r\n");
    	sb.append("\t\treturn example; \r\n");
    	sb.append("\t}\n");


		sb.append("}");
		return sb.toString();
	}

	private String parseController() {
		StringBuffer sb = new StringBuffer();
    	sb.append("package "+ControllerPackge+"; \r\n");
    	sb.append("import org.springframework.web.bind.annotation.RestController; \r\n");
    	sb.append("import org.springframework.web.bind.annotation.PostMapping;   \r\n");
    	sb.append("import org.springframework.web.bind.annotation.RequestMapping;   \r\n");
    	sb.append("import org.springframework.web.bind.annotation.RequestHeader;   \r\n");
    	sb.append("import springfox.documentation.annotations.ApiIgnore;  \r\n");

    	sb.append("import org.slf4j.Logger; \r\n");
    	sb.append("import org.slf4j.LoggerFactory; \r\n");
    	sb.append("import "+EntityPackge+"."+EntityName+"; \r\n");
    	sb.append("import "+EntityDOPackge+"."+EntityName+"AD; \r\n");
    	sb.append("import "+EntityVOPackge+"."+EntityVOName+"; \r\n");
    	sb.append("import "+EntityDOPackge+"."+EntityDOName+"; \r\n");
    	sb.append("import "+SvervicePackge+".I"+EntityName+"Service; \r\n");
    	sb.append("import "+DaoPackge+"."+EntityName+"Mapper; \r\n");
    	sb.append("import org.springframework.beans.factory.annotation.Autowired;   \r\n");
    	sb.append("import "+utilpackageName+"EntityChangeRquestView;   \r\n");
    	
    	sb.append("import io.swagger.annotations.Api;   \r\n");
    	sb.append("import io.swagger.annotations.ApiImplicitParam;   \r\n");
    	sb.append("import io.swagger.annotations.ApiImplicitParams;   \r\n");
    	sb.append("import java.util.List;  \r\n");
    	sb.append("import io.swagger.annotations.ApiOperation;  \r\n");
    	sb.append("import "+utilpackageName+"ResultObject;  \r\n");
    	sb.append("import com.github.pagehelper.PageInfo; \r\n");
    	sb.append("import "+utilpackageName+"LayuiPage; \r\n");
    	sb.append("import "+utilpackageName+"ResultUtil;  \r\n\n");
    	sb.append("import "+utilpackageName+"StringUtils;  \r\n\n");
    	sb.append("/** \r\n");
    	sb.append("* "+expirePlatName+SoureTableComment+"控制器 \r\n");
    	sb.append("* Auther:"+auther+"\r\n");
    	sb.append("* Date:"+TimeUtils.getCurrentTime()+"\r\n");
    	sb.append("*/ \r\n");
    	sb.append("@RestController \r\n");
    	sb.append("@RequestMapping(\""+this.initMin(EntityName)+ControllerVersion+"\") \r\n");
    	sb.append("@Api(tags = \""+expirePlatName+SoureTableComment+"的接口\") \r\n");
    	sb.append("public class "+EntityName+"Controller {  \r\n");
    	sb.append("\t@Autowired \r\n");
    	sb.append("\tprivate I"+EntityName+"Service i"+EntityName+"Service; \r\n");
    	sb.append("\t@Autowired \r\n");
    	sb.append("\tprivate "+EntityName+"Mapper i"+EntityName+"Mapper; \r\n");
    	sb.append("\r\n\n");
    	sb.append("Logger logger=LoggerFactory.getLogger("+EntityName+"Controller.class);");
    	sb.append("\t/** \r\n");
    	sb.append("\t* 依据ID获取"+SoureTableComment+"详情 \r\n");
    	sb.append("\t* Auther:"+auther+"\r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@PostMapping(\"get"+EntityName+"SingleById\") \r\n");
    	sb.append("\t@ApiOperation(\"依据ID获取"+SoureTableComment+"详情\") \r\n");
    	sb.append("\t@ApiImplicitParams({ @ApiImplicitParam(name = \""+MinEntityName+"Id\", value = \""+SoureTableComment+"的id\", required = false,example=\"1\") })\r\n");
    	sb.append("\tpublic  ResultObject "+"get"+EntityName+"SingleById"+"("+colTypes[0]+" "+MinEntityName+"Id"+expireParam+") {  \r\n");
    	sb.append("\t\ttry{ \r\n");
    	sb.append("\t\t  "+EntityVOName+" entity=new "+EntityVOName+"(); \r\n");
    	sb.append("\t\t  entity=i"+EntityName+"Service.getSingleInfoById("+MinEntityName+"Id); \r\n");
    	sb.append("\t\t  return ResultUtil.successData(entity); \r\n");
    	sb.append("\t\t}catch(Exception e){ \r\n");
    	sb.append("\t\t  logger.error(e+\"依据ID获取"+SoureTableComment+"详情异常\"); \r\n");
    	sb.append("\t\t  return ResultUtil.error(\"依据ID获取"+SoureTableComment+"详情异常\"); \r\n");
    	sb.append("\t\t} \r\n");
    	sb.append("\t} \r\n");
    	sb.append("\t/** \r\n");
    	sb.append("\t* 获取"+SoureTableComment+"单条记录 \r\n");
    	sb.append("\t* Auther:"+auther+"\r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@PostMapping(\"get"+EntityName+"Single\") \r\n");
    	sb.append("\t@ApiOperation(\"获取"+SoureTableComment+"单条记录\") \r\n");
    	sb.append("\t@ApiImplicitParams({  })\r\n");
    	sb.append("\tpublic ResultObject "+"get"+EntityName+"Single"+"("+EntityDOName+" "+MinEntityName+expireParam+") {  \r\n");
    	sb.append("\t\ttry{ \r\n");
    	sb.append("\t\t "+EntityVOName+" "+MinEntityName+"VO=i"+EntityName+"Service.getSingleInfo("+MinEntityName+"); \r\n");
    	sb.append("\t\t  return ResultUtil.successData("+MinEntityName+"VO); \r\n");
    	sb.append("\t\t}catch(Exception e){ \r\n");
    	sb.append("\t\t  logger.error(e+\"获取"+SoureTableComment+"单条记录异常\"); \r\n");
    	sb.append("\t\t  return ResultUtil.error(\"获取"+SoureTableComment+"单条记录异常\"); \r\n");
    	sb.append("\t\t} \r\n");
    	sb.append("\t} \r\n");
    	sb.append("\t/** \r\n");

    	sb.append("\t* 获取"+SoureTableComment+"列表 \r\n");
    	sb.append("\t* Auther:"+auther+"\r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@PostMapping(\"get"+EntityName+"List\") \r\n");
    	sb.append("\t@ApiOperation(\"获取"+SoureTableComment+"列表\") \r\n");
    	sb.append("\t@ApiImplicitParams({ })\r\n");
    	sb.append("\tpublic ResultObject "+"get"+EntityName+"List"+"("+EntityDOName+" "+MinEntityName+expireParam+") {  \r\n");
    	sb.append("\t\ttry{ \r\n");
    	sb.append("\t\t  List<"+EntityVOName+"> lst = i"+EntityName+"Service.get"+EntityName+"List("+MinEntityName+"); \r\n");
    	sb.append("\t\t  return ResultUtil.successData(lst); \r\n");
    	sb.append("\t\t}catch(Exception e){ \r\n");
    	sb.append("\t\t  logger.error(e+\"获取"+SoureTableComment+"列表记录异常\"); \r\n");
    	sb.append("\t\t  return ResultUtil.error(\"获取"+SoureTableComment+"列表记录异常\"); \r\n");
    	sb.append("\t\t} \r\n");
    	sb.append("\t} \r\n");

    	sb.append("\t/** \r\n");
    	sb.append("\t* 获取"+SoureTableComment+"分页数据 \r\n");
    	sb.append("\t* Auther:"+auther+"\r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@PostMapping(\"get"+EntityName+"ListByPage\") \r\n");
    	sb.append("\t@ApiOperation(\"获取"+SoureTableComment+"分页数据\") \r\n");
    	sb.append("\t@ApiImplicitParams({@ApiImplicitParam(name = \"pageNum\", value = \"当前页\", required = true,example=\"1\"), \r\n");
    	sb.append("\t@ApiImplicitParam(name = \"pageSize\", value = \"每页大小\", required = true,example=\"1\"), \r\n");
    	sb.append("\t@ApiImplicitParam(name = \"sort\", value = \"排序依据字段\", required = false,example=\"1\"), \r\n");
    	sb.append("\t@ApiImplicitParam(name = \"dire\", value = \"倒序还是正序  asc | desc\", required = false,example=\"1\")\r\n");
    	sb.append("})\r\n");
    	sb.append("\tpublic LayuiPage "+"get"+EntityName+"ListByPage"+"("+EntityDOName+" "+MinEntityName+",LayuiPage<"+EntityVOName+"> layuiPage"+expireParam+") {  \r\n");
    	sb.append("\t\ttry{ \r\n");


    	sb.append("\t\t  return i"+EntityName+"Service.get"+EntityName+"ListByPage("+MinEntityName+", layuiPage); \r\n");
    	//sb.append("\t\t  return ResultUtil.successData(pageInfo); \r\n");

    	sb.append("\t\t}catch(Exception e){ \r\n");
    	sb.append("\t\t  logger.error(e+\"获取"+SoureTableComment+"分页记录异常\"); \r\n");
    	sb.append("\t\t  return layuiPage;\r\n");
    	sb.append("\t\t} \r\n");
    	sb.append("\t} \r\n");
      	sb.append("\t/** \r\n");
    	sb.append("\t* 添加"+SoureTableComment+"方法 \r\n");
    	sb.append("\t* Auther:"+auther+"\r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@PostMapping(\"add"+EntityName+"\") \r\n");
    	sb.append("\t@ApiOperation(\"添加"+SoureTableComment+"\") \r\n");
    	sb.append("\t@ApiImplicitParams({  })\r\n");
//    	sb.append("\t@ApiImplicitParams({ @ApiImplicitParam(name = \""+MinEntityName+"\", value = \""+SoureTableComment+"的数据\", required = false) })\r\n");
    	sb.append("\tpublic  ResultObject "+"add"+EntityName+"("+EntityName+"AD "+MinEntityName+"ad"+expireParam+") {  \r\n");
    	sb.append("\t\ttry{ \r\n");
    	sb.append("\t\t  "+EntityName+" "+MinEntityName+"=EntityChangeRquestView.createDOToEntity("+MinEntityName+"ad, new "+EntityName+"()); \r\n");
    	
    	sb.append("\t\t  return i"+EntityName+"Service.addNew"+EntityName+"("+MinEntityName+"); \r\n");
    	sb.append("\t\t  //return ResultUtil.success(\"成功\"); \r\n");
    	sb.append("\t\t}catch(Exception e){ \r\n");
    	sb.append("\t\t  logger.error(e+\"添加"+SoureTableComment+"异常\"); \r\n");
    	sb.append("\t\t  return ResultUtil.error(\"添加"+SoureTableComment+"异常\"); \r\n");
    	sb.append("\t\t} \r\n");
    	sb.append("\t} \r\n");

    	sb.append("\t/** \r\n");
    	sb.append("\t* 修改"+SoureTableComment+"方法 \r\n");
    	sb.append("\t* Auther:"+auther+"\r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@PostMapping(\"mod"+EntityName+"\") \r\n");
    	sb.append("\t@ApiOperation(\"修改"+SoureTableComment+"\") \r\n");
    	sb.append("\t@ApiImplicitParams({  })\r\n");
    	sb.append("\tpublic  ResultObject "+"mod"+EntityName+"("+EntityName+"AD "+MinEntityName+"ad"+expireParam+") {  \r\n");
    	sb.append("\t\ttry{ \r\n");
    	sb.append("\t\t  "+EntityName+" "+MinEntityName+"=EntityChangeRquestView.createDOToEntity("+MinEntityName+"ad, new "+EntityName+"()); \r\n");
    	
    	sb.append("\t\t\t  return i"+EntityName+"Service.mod"+EntityName+"("+MinEntityName+"); \r\n");
    	sb.append("\t\t\t  //return ResultUtil.success(\"成功\"); \r\n");
    	sb.append("\t\t}catch(Exception e){ \r\n");
    	sb.append("\t\t  logger.error(e+\"修改"+SoureTableComment+"异常\"); \r\n");
    	sb.append("\t\t  return ResultUtil.error(\"修改"+SoureTableComment+"异常\"); \r\n");
    	sb.append("\t\t} \r\n");
    	sb.append("\t} \r\n");


    	sb.append("\t/** \r\n");
		sb.append("\t* 删除" + SoureTableComment + " \r\n");
		sb.append("\t* Auther:"+auther+"\r\n");
		sb.append("\t*/ \r\n");
		sb.append("\t@PostMapping(\"del" + EntityName + "\") \r\n");
		sb.append("\t@ApiOperation(\"删除" + SoureTableComment + "方法\") \r\n");
		sb.append("\t@ApiImplicitParams({ @ApiImplicitParam(name = \"" + MinEntityName
				+ "Ids\", value = \"主键id数据\",example=\"1\", required = false) })\r\n");
		sb.append("\tpublic  ResultObject " + "del" + EntityName + "(String " + MinEntityName
//				sb.append("\tpublic  ResultObject " + "del" + EntityName + "(" + colTypes[0] + " " + MinEntityName
				+ "Ids"+expireParam+") {  \r\n");
		sb.append("\t\ttry{ \r\n");
		sb.append("\t\t  if(StringUtils.isNull("+MinEntityName+"Ids)) {\r\n");
		sb.append("\t\t	  return ResultUtil.error(\"错误的参数\");\r\n");
		sb.append("\t\t  }\r\n");
		sb.append("\t\t String[] strs="+MinEntityName+"Ids.split(\",\");\r\n");
		sb.append("\t\t for(String str:strs) {\r\n");
		sb.append("\t\t	  i"+EntityName+"Mapper.deleteByPrimaryKey(str);\r\n");
		sb.append("\t\t }\r\n");

		sb.append("\t\t return ResultUtil.success();  \r\n");
//		sb.append("\t\t  " + EntityName + " " + MinEntityName + "=new " + EntityName + "(); \r\n");
//		sb.append("\t\t  " + MinEntityName + ".set" + EntityName + "Id(" + MinEntityName + "Id); \r\n");
//		sb.append("\t\t  return i" + EntityName + "Service.del" + EntityName + "(" + MinEntityName + "); \r\n");
//		sb.append("\t\t  //return ResultUtil.success(\"成功\"); \r\n");
		sb.append("\t\t}catch(Exception e){ \r\n");
		sb.append("\t\t  logger.error(e+\"删除" + SoureTableComment + "方法异常 \"); \r\n");
		sb.append("\t\t  return ResultUtil.error(\"删除" + SoureTableComment + "方法异常 \"); \r\n");
		sb.append("\t\t} \r\n");
		sb.append("\t} \r\n");
    	sb.append("}");
    	return sb.toString();
    }

	private String parseMapper() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>  \r\n");
		sb.append(
				"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" > \r\n");
		sb.append("<mapper namespace=\"" + DaoPackge + ".I" + EntityName + "Dao\"> \r\n");
		sb.append("\t<resultMap id=\"BaseResultMap\" type=\"" + EntityPackge + "." + EntityName
				+ "\"></resultMap>   \r\n");
		sb.append("\t<sql id=\"Base_Column_List\"> \r\n");
		sb.append("\t\t" + getSumString(colnames) + "\r\n");
		sb.append("\t</sql> \r\n");
		// 单一查询方法
		sb.append("\t<select id=\"getSingleInfo\" resultMap=\"BaseResultMap\" parameterType=\"" + EntityPackge + "."
				+ EntityName + "\" > \r\n");
		sb.append(
				"\t\tselect  <include refid=\"Base_Column_List\" />  from " + SoureEntityName + " where id=#{id} \r\n");
		sb.append("\t</select> \r\n");
		// 获取总页数方法
		sb.append("\t<select id=\"getSumCount\" resultType=\"int\" parameterType=\"" + EntityPackge + "." + EntityName
				+ "\" > \r\n");
		sb.append("\t\tselect  count(1)  from " + SoureEntityName + " \r\n");
		sb.append("\t</select> \r\n");
		// 分页查询
		sb.append("\t<select id=\"getListPagerInfo\" resultMap=\"BaseResultMap\" parameterType=\"map\"> \r\n");
		sb.append(
				"\t\tselect <include refid=\"Base_Column_List\" /> from " + SoureEntityName + " order by id desc \r\n");
		sb.append("\t\tlimit #{limitMax} offset #{limitMin} \r\n");
		sb.append("\t</select> \r\n");
		// 删除
		sb.append("\t<delete id=\"delSingleInfo\" parameterType=\"" + EntityPackge + "." + EntityName + "\"> \r\n");
		sb.append("\t\tdelete from " + SoureEntityName + " where id=#{id} \r\n");
		sb.append("\t</delete> \r\n");
		// 添加
		sb.append("\t<insert id=\"addSingleInfo\" parameterType=\"" + EntityPackge + "." + EntityName
				+ "\" useGeneratedKeys=\"true\" keyProperty=\"id\"> \r\n");
		sb.append("\t\tinsert into " + SoureEntityName + "  \r\n");
		sb.append("\t\t(" + getAddSumString(colnames) + ") \r\n");
		sb.append("\t\tvalues ( " + getFormatMapping(colnames) + " )\r\n");
		sb.append("\t</insert> \r\n");
		// 修改
		sb.append("\t<update id=\"modSingleInfo\" parameterType=\"" + EntityPackge + "." + EntityName + "\">   \r\n");
		sb.append("\tupdate " + SoureEntityName + " \r\n");
		sb.append("\t<set> \r\n");
		sb.append(getFormatIf(colnames));
		sb.append("\t</set> \r\n");
		sb.append("\twhere id=#{id} \r\n");
		sb.append("\t</update> \r\n");
		sb.append("</mapper> \r\n");
		return sb.toString();
	}

	/**
	 * 解析处理(生成实体类主体代码) vtype :entity,vo,do
	 */
	private String parse(String[] colNames, String[] colTypes, int[] colSizes, String vtype) {
		StringBuffer sb = new StringBuffer();
		String className = EntityName + vtype;
		Boolean isTable=false;
		if ("VO".equals(vtype)) {
			sb.append("package " + EntityVOPackge + "; \r\n");
		} else if ("DO".equals(vtype)) {
			sb.append("package " + EntityDOPackge + "; \r\n");
		} else if ("AD".equals(vtype)) {
			sb.append("package " + EntityDOPackge + "; \r\n");
		}  else {
			sb.append("package " + EntityPackge + "; \r\n");
			className = EntityName;
			isTable=true;
		}
		sb.append("\r\nimport javax.persistence.Column;");
		sb.append("\r\nimport javax.persistence.GeneratedValue;");
		sb.append("\r\nimport javax.persistence.Id;");
		sb.append("\r\nimport javax.persistence.Table;");
		sb.append("\r\nimport java.math.BigDecimal;");
		sb.append("\r\nimport "+utilpackageName+"StringUtils;");
		sb.append("\r\nimport io.swagger.annotations.ApiModel;");
		sb.append("\r\nimport io.swagger.annotations.ApiModelProperty;\r\n");
		if (importUtil) {
			sb.append("import java.util.Date;\r\n");
		}
		if (importSql) {
			// sb.append("import java.sql.*;\r\n\r\n");
		}
		if (importMath) {
			// sb.append("import java.math.*;\r\n\r\n");
		}
		// 表注释
		processColnames(sb);
		sb.append("/** \r\n");
		sb.append("* " + EntityName + "实体层 \r\n");
		sb.append("* Auther:"+auther+"\r\n");
		sb.append("* Date:" + TimeUtils.getCurrentTime() + "\r\n");
		sb.append("*/ \r\n");
		if (vtype.equals("entity")) {
			sb.append("\r\n@Table(name = \"" + SoureEntityName + "\")");
		}
		sb.append("\r\n@ApiModel(\"" + SoureTableComment + "\")\r\n");
		sb.append("public class " + className + " {\r\n");
		processAllAttrs(sb,vtype);
		processAllMethod(sb,vtype);
		sb.append("}\r\n");
		return sb.toString();

	}

	/**
	 * 构造字符串,用，隔开 Author:FENG 2016年5月11日
	 */
	private String getSumString(String[] strs) {
		String s = "";
		for (String i : strs) {
			s += i + ",";
		}
		s = s.substring(0, s.lastIndexOf(","));
		return s;
	}

	/**
	 * 构造字符串,用，隔开 Author:FENG 2016年5月11日
	 */
	private String getAddSumString(String[] strs) {
		String s = "";
		Boolean t = true;
		for (String i : strs) {
			if (t) {
				t = false;
				continue;
			} else {
				s += i + ",";
			}
		}
		if (s.indexOf(",") != -1) {

			s = s.substring(0, s.lastIndexOf(","));
		}
		return s;
	}

	/**
	 * 构造字符串使用#{}包含 Author:FENG 2016年5月11日
	 */
	private String getFormatMapping(String[] strs) {
		String s = "";
		Boolean c = true;
		for (String i : strs) {
			if (c) {
				c = false;
				continue;
			} else {
				String d = "#{" + i + "}";
				s += d + ",";
			}
		}
		s = s.substring(0, s.lastIndexOf(","));
		return s;
	}

	/**
	 * 构造字符串使用if()包含 Author:FENG 2016年5月11日
	 */
	private String getFormatIf(String[] strs) {
		// <if test="memberId != null">
		// memberId = #{memberId},
		// </if>
		String s = "";
		Boolean c = true;
		for (String i : strs) {
			if (c) {
				c = false;
				continue;
			} else {
				StringBuffer sb = new StringBuffer();
				sb.append("\t\t<if test=\"" + i + " !=null\"> \r\n");
				sb.append("\t\t\t" + i + "=#{" + i + "}" + ",\r\n");
				sb.append("\t\t</if>\r\n");
				s += sb.toString();
			}
		}
		s = s.substring(0, s.lastIndexOf(",")) + s.substring(s.lastIndexOf(",") + 1);
		return s;
	}

	/**
	 * 处理列名,把空格下划线'_'去掉,同时把下划线后的首字母大写 要是整个列在3个字符及以内,则去掉'_'后,不把"_"后首字母大写.
	 * 同时把数据库列名,列类型写到注释中以便查看,
	 *
	 * @param sb
	 */
	private void processColnames(StringBuffer sb) {
		// sb.append("\r\n/** " + EntityName + "\r\n");
		String colsiz = "";
		for (int i = 0; i < colnames.length; i++) {
			colsiz = colSizes[i] <= 0 ? ""
					: (colScale[i] <= 0 ? "(" + colSizes[i] + ")" : "(" + colSizes[i] + "," + colScale[i] + ")");
			// sb.append("\t" + colnames[i].toUpperCase() +" "+colTypes[i].toUpperCase()+
			// colsiz+"\r\n");
			char[] ch = colnames[i].toCharArray();
			char c = 'a';
			if (ch.length > 3) {
				for (int j = 0; j < ch.length; j++) {
					c = ch[j];
					if (c == '_') {
						if (ch[j + 1] >= 'a' && ch[j + 1] <= 'z') {
							ch[j + 1] = (char) (ch[j + 1] - 32);
						}
					}
				}
			}
			String str = new String(ch);
			colnames[i] = str.replaceAll("_", "");
		}
		// sb.append("*/\r\n");
	}

	/**
	 * 生成所有的方法
	 *
	 * @param sb
	 */
	private void processAllMethod(StringBuffer sb,String vtype) {
		for (int i = 0; i < colnames.length; i++) {
//			if(vtype.contentEquals("VO") && colnames[i].contentEquals("orgCode")) {
//				continue;
//			}
			sb.append("\t/**\r\n");
			sb.append("\t *"+colremarks[i]+"\r\n");
			sb.append("\t */ \r\n");
			sb.append("\tpublic void set" + initMen(colnames[i]) + "(" + colTypes[i] + " " + colnames[i] + "){\r\n");
			sb.append("\t\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
			sb.append("\t}\r\n");
			sb.append("\t/**\r\n");
			sb.append("\t *"+colremarks[i]+"\r\n");
			sb.append("\t */ \r\n");
			sb.append("\tpublic " + colTypes[i] + " get" + initMen(colnames[i]) + "(){\r\n");
			if(colnames[i].contains("Time")) {
				sb.append("\t\t  if(StringUtils.isNotNull(" + colnames[i] + ")) {if(" + colnames[i] + ".contains(\".0\")) {return " + colnames[i] + ".replace(\".0\", \"\");}}return " + colnames[i] + ";\r\n");
			}else if(colTypes[i].equals("Float")){

				sb.append("\t\treturn NumberUtils.KeepDecimalFloat(" + colnames[i] + ");\r\n");
			}else if( colTypes[i].equals("Double")){

				sb.append("\t\treturn NumberUtils.KeepDecimalDe(" + colnames[i] + ");\r\n");
			}else if((vtype.contentEquals("DO") || vtype.contentEquals("AD")) &&colnames[i].equals("orgCode")) {
				sb.append("\t\treturn "+utilpackageName+"OrgCodeGreater.decode(" + colnames[i] + ");\r\n");
			}else if(vtype.contentEquals("VO") &&colnames[i].equals("orgCode")){
				sb.append("\t\treturn "+utilpackageName+"OrgCodeGreater.encode(" + colnames[i] + ");\r\n");
			}else {
				sb.append("\t\treturn " + colnames[i] + ";\r\n");
			}
			sb.append("\t}\r\n");
			if((vtype.contentEquals("DO") || vtype.contentEquals("AD")) &&colnames[i].equals("orgCode")) {
				sb.append("\tpublic String getSourceOrgCode(){\r\n");
				sb.append("\t\treturn this.orgCode;\r\n");
				sb.append("\t}\r\n");
			}
		}
	}

	/**
	 * 解析输出属性
	 *
	 * @return
	 */
	private void processAllAttrs(StringBuffer sb, String vtype) {
		sb.append("\tprivate static final long serialVersionUID = 1L;\r\n");
		for (int i = 0; i < colnames.length; i++) {
			//if(vtype.contentEquals("VO") && colnames[i].contentEquals("orgCode")) {
				//continue;
			//}
			if (vtype.contentEquals("entity")) {

				if (i == 0) {
					sb.append("\t@Id\r\n");
					sb.append("\t@GeneratedValue(generator = \"JDBC\")\r\n");
				}
				sb.append("\t@Column(name=\"" + colSourceNames[i] +"\")\r\n");

			}
			sb.append("\t@ApiModelProperty(value = \"" + colremarks[i] + "\""+getSwagger(colSourceTypes[i])+")\r\n");
			if(colnames[i].equals("delState")) {

				sb.append("\tprivate " + colTypes[i] + " " + colnames[i] + "=0;\r\n\r\n");
			}else {

				sb.append("\tprivate " + colTypes[i] + " " + colnames[i] + ";\r\n\r\n");
			}
		}

		sb.append("\r\n");
	}


	private String getSwagger(String sqlType) {
		if (sqlType == null) {
			System.out.println("有一个空");
			return "";
		}
		if (sqlType.equals("int")) {
			return ",example=\"1\"";
		} else if (sqlType.equals("long")) {
			return ",example=\"1\"";
		} else if (sqlType.equals("bigint") || sqlType.equals("BIGINT")) {
			return ",example=\"1\"";
		} else if (sqlType.equals("float") || sqlType.equals("float precision")) {
			return ",example=\"1\"";
		} else if (sqlType.equals("double") || sqlType.equals("double precision")) {
			return ",example=\"1\"";

		} else if (sqlType.equals("number") || sqlType.equals("decimal") || sqlType.equals("numeric")
				|| sqlType.equals("real")) {
			return ",example=\"1\"";
		}
		return "";
	}

	/**
	 * 根据规则修改文件名即表名
	 *
	 * @param str
	 * @return
	 */
	private String initcap(String str) {
		// 分割字符串
		String[] strs = str.split("_");
		String result = "";
		for (String st : strs) {
			char[] ch = st.toCharArray();
			// 将首字母变为大写
			if (ch[0] >= 'a' && ch[0] <= 'z') {
				ch[0] = (char) (ch[0] - 32);
			}
			String so = new String(ch);
			result += so;
		}
		return result;
	}

	/**
	 * 去掉前缀
	 *
	 * @param sou
	 * @return 2019年9月3日 作者：fengchase
	 */
	private String prefix(String sou) {
		if ("".equals(Pre_fix)) {
			System.out.println("没有前缀配置。");
			return sou;
		} else {
			String re = sou.substring(Pre_fix.length());
			System.out.println(sou + "--" + Pre_fix + "--" + re);
			return re;
		}
	}

	/**
	 * 把输入字符串的首字母改成大写
	 *
	 * @param str
	 * @return
	 */
	private String initMen(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}

	/**
	 * 把输入字符串的首字母改成小写
	 *
	 * @param str
	 * @return
	 */
	private String initMin(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'A' && ch[0] <= 'Z') {
			ch[0] = (char) (ch[0] + 32);
		}
		return new String(ch);
	}

	// 其他数据库不需要这个方法 oracle和db2需要
	private static String getSchema(Connection conn) throws Exception {
		String schema;
		schema = conn.getMetaData().getUserName();
		if ((schema == null) || (schema.length() == 0)) {
			throw new Exception("ORACLE数据库模式不允许为空");
		}
		return schema.toUpperCase().toString();

	}

	/**
	 * Oracle
	 *
	 * @param sqlType
	 * @param scale
	 * @return
	 */
	private String DataBaseDataType(String sqlType, int scale, int size) {
		if (sqlType.equals("int")) {
			return "Integer";
		} else if (sqlType.equals("long")) {
			return "Long";
		} else if (sqlType.equals("bigint") || sqlType.equals("BIGINT")) {
			return "Long";
		} else if (sqlType.equals("float") || sqlType.equals("float precision")) {
			return "Float";
		} else if (sqlType.equals("double") || sqlType.equals("double precision")) {
			return "Double";

		}else if( sqlType.equals("decimal") ) {
			return "BigDecimal";
		} else if (sqlType.equals("number") || sqlType.equals("numeric")
				|| sqlType.equals("real")) {
			return scale == 0 ? (size < 10 ? "Integer" : "Long") : "Long";
		} else if (sqlType.equals("varchar") || sqlType.equals("varchar2") || sqlType.equals("char")
				|| sqlType.equals("nvarchar") || sqlType.equals("text") || sqlType.equals("nchar")) {
			return "String";
		} else if (sqlType.equals("datetime") || sqlType.equals("date") || sqlType.equals("timestamp")) {
			return "String";
		}
		System.out.println(sqlType);
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BeanUtil t = new BeanUtil();
		try {
			t.tableToEntity();
			System.out.println("完成1..");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
