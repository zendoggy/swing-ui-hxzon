package org.hxzon.goodcode;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.VisitorSupport;
import org.dom4j.XPath;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.XMLErrorHandler;

public class Dom4jUtils {
	private String XMLPath = null;
	private Document document = null;

	public Dom4jUtils() {
	}

	public Dom4jUtils(String XMLPath) {
		this.XMLPath = XMLPath;
	}

	public void openXML() {
		try {
			SAXReader reader = new SAXReader();
			this.document = reader.read(this.XMLPath);
			System.out.println("openXML() successful ...");
		} catch (Exception e) {
			System.out.println("openXML() Exception:" + e.getMessage());
		}
	}

	public void createXML(String rootName) {
		try {
			this.document = DocumentHelper.createDocument();
			document.addElement(rootName);
			System.out.println("createXML() successful...");
		} catch (Exception e) {
			System.out.println("createXML() Exception:" + e.getMessage());
		}
	}

	public void openXML(String filePath) {
		try {
			SAXReader saxReader = new SAXReader();
			this.document = saxReader.read(filePath);
			System.out.println("openXML(String filePath) successful ...");
		} catch (Exception e) {
			System.out.println("openXML() Exception:" + e.getMessage());
		}
	}

	public void saveXML() {
		try {
			XMLWriter output = new XMLWriter(new FileWriter(new File(
					this.XMLPath)));
			output.write(document);
			output.close();
			System.out.println("saveXML() successful ...");
		} catch (Exception e1) {
			System.out.println("saveXML() Exception:" + e1.getMessage());
		}
	}

	public void saveXML(String toFilePath) {
		try {
			XMLWriter output = new XMLWriter(new FileWriter(
					new File(toFilePath)));
			output.write(document);
			output.close();
		} catch (Exception e1) {
			System.out.println("saveXML() Exception:" + e1.getMessage());
		}
	}

	public void addNodeToRoot(String nodeName, String nodeValue) {
		Element root = this.document.getRootElement();
		Element level1 = root.addElement(nodeName);
		level1.addText(nodeValue);
	}

	public Element getRoot() {
		return this.document.getRootElement();
	}

	public XPath createXPath(String e) {
		return this.document.createXPath(e);
	}

	public List selectNodes(String e) {
		try {
			return document.selectNodes(e);
		} catch (Exception e1) {
			System.out
					.println("getElementValue() Exception：" + e1.getMessage());
			return null;
		}
	}

	public String getElementValue(String nodeName) {
		try {
			Node node = document.selectSingleNode("//" + nodeName);
			return node.getText();
		} catch (Exception e1) {
			System.out
					.println("getElementValue() Exception：" + e1.getMessage());
			return null;
		}
	}

	public String getElementValue(String nodeName, String childNodeName) {
		try {
			Node node = this.document.selectSingleNode("//" + nodeName + "/"
					+ childNodeName);
			return node.getText();
		} catch (Exception e1) {
			System.out
					.println("getElementValue() Exception：" + e1.getMessage());
			return null;
		}
	}

	public void setElementValue(String nodeName, String nodeValue) {
		try {
			Node node = this.document.selectSingleNode("//" + nodeName);
			node.setText(nodeValue);
		} catch (Exception e1) {
			System.out
					.println("setElementValue() Exception:" + e1.getMessage());
		}
	}

	public void setElementValue(String nodeName, String childNodeName,
			String nodeValue) {
		try {
			Node node = this.document.selectSingleNode("//" + nodeName + "/"
					+ childNodeName);
			node.setText(nodeValue);
		} catch (Exception e1) {
			System.out
					.println("setElementValue() Exception:" + e1.getMessage());
		}
	}

	public static void main(String args[]) {
		// Dom4jUtils dom=new
		// Dom4jUtils("H:\\backup\\gma\\sanjiangzuixinCID\\X7212_2LB1_GO.XML");
		// dom.openXML();
		// Element root=dom.getRoot();
		// root.accept(new MyVisitor());
		// System.out.println(root.getName());
		validation();
	}

	public static void validation() {
		String xmlFileName = "H:\\backup\\gma\\sanjiangzuixinCID\\X7212_2LB2_GO.xml";
		//X7212_2LB1_GO.XML
//		xmlFileName = "H:\\backup\\gma\\nanruijibao\\PCS902.cid";
		String xsdFileName = Dom4jUtils.class.getResource("SCL.xsd").toString().substring(5);
		// <SCL xmlns="http://www.iec.ch/61850/2003/SCL"
		// xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		// xsi:schemaLocation="http://www.iec.ch/61850/2003/SCL
		// D:\DigitalSubstation\SCLschema\SCL.xsd">

		try {
			// 创建默认的XML错误处理器
			XMLErrorHandler errorHandler = new XMLErrorHandler();
			// 获取基于 SAX 的解析器的实例
			SAXParserFactory factory = SAXParserFactory.newInstance();
			// 解析器在解析时验证 XML 内容。
			factory.setValidating(true);
			// 指定由此代码生成的解析器将提供对 XML 名称空间的支持。
			factory.setNamespaceAware(true);
			// 使用当前配置的工厂参数创建 SAXParser 的一个新实例。
			SAXParser parser = factory.newSAXParser();
			// 创建一个读取工具
			SAXReader xmlReader = new SAXReader();
			// 获取要校验xml文档实例
			Document xmlDocument = (Document) xmlReader.read(new File(
					xmlFileName));
			// 设置 XMLReader 的基础实现中的特定属性。核心功能和属性列表可以在
			// [url]http://sax.sourceforge.net/?selected=get-set[/url] 中找到。
			parser.setProperty(
					"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
					"http://www.w3.org/2001/XMLSchema");
			parser.setProperty(
					"http://java.sun.com/xml/jaxp/properties/schemaSource",
					"file:" + xsdFileName);
			// 创建一个SAXValidator校验工具，并设置校验工具的属性
			SAXValidator validator = new SAXValidator(parser.getXMLReader());
			// 设置校验工具的错误处理器，当发生错误时，可以从处理器对象中得到错误信息。
			validator.setErrorHandler(errorHandler);
			// 校验
			validator.validate(xmlDocument);

			 XMLWriter writer = new
			 XMLWriter(OutputFormat.createPrettyPrint());
			// 如果错误信息不为空，说明校验失败，打印错误信息
			if (errorHandler.getErrors().hasContent()) {
				System.out.println("XML文件通过XSD文件校验失败！");
				writer.write(errorHandler.getErrors());
			} else {
				System.out.println("Good! XML文件通过XSD文件校验成功！");
			}
		} catch (Exception ex) {
			System.out.println("XML文件: " + xmlFileName + " 通过XSD文件:"
					+ xsdFileName + "检验失败。\n原因： " + ex.getMessage());
			ex.printStackTrace();
		}
	}

}

class MyVisitor extends VisitorSupport {
	public void visit(Element element) {
		System.out.println(element.getName());
	}

	public void visit(Attribute attr) {
		System.out.println(attr.getName());
	}
}