package org.hxzon.newui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.hxzon.util.OgnlUtils;
import org.hxzon.util.TextValidationList;
import org.hxzon.util.ValidationManager;
import org.hxzon.util.ValidationManager.ValidationListener;


//import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

public class Main {

	public static void main(String[] args) {
		new AppProperties();
		try {
			// UIManager.setLookAndFeel(new WindowsLookAndFeel());
			// UIManager.put("Table.gridColor", new Color(195,218,249));
			// // added by pianyao, 2009.7.15 ->
			// UIDefaults table = UIManager.getLookAndFeelDefaults();
			// //table.put("SplitPaneDivider.border", new
			// LineBorder(Color.LIGHT_GRAY));
			// table.put("SplitPane.darkShadow", Color.LIGHT_GRAY);
			// // <- adding finished
		} catch (Exception ex1) {
			System.err.println("Unsupported LookAndFeel: ");
		}
		textCheck();
		// hpage();
		// ognl();
		// tree();
	}

	public static void hpage() {
		HPage page = new HPage();
		// f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		HPanel form = new HPanel();
		// form.add(new HText("hello:"));
		// form.add(new HText("o:"));
		// form.add(new HText("hut:"), true);
		form.addLabel("helllllllllll");
		form.add(new JSpinner(), "");
		// form.addFill(new HText("hut:"), true);
		// form.addFill(new HText("hut:"), true);
		page.add(form, "grow");
		List list = new ArrayList();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		HCheckBoxGroup checkBox = new HCheckBoxGroup(list, "欢迎光临");
		// f.add(checkBox,"newline,grow,pushx,pushy");
		// f.setSize(500, 480);
		// f.showErrorMessage("wrong");
		// TextCheckerUtils c = new TextCheckerUtils();
		// c.addChecker("int", "min=5;typeError=不是数字;rangeError=需大于5");
		// JTextField field = new JTextField(10);
		// f.add(field, "newline,grow,push");
		// HSwingUtils.bindChecker(field, "数字", c, f.getTitlePanel());
		HInt field = new HInt("id", "数字", true, "3", null);
		form.add(field, true);
		final List<Student> model = new ArrayList<Student>();
		for (int i = 0; i < 100; i++) {
			model.add(new Student());
		}
		final List<Integer> editable = new ArrayList<Integer>();
		editable.add(2);
		editable.add(3);
		editable.add(4);
		final HTable table = new HTable();
		table.setRow(model);
		table.setColumn("name", "obj", "id", "id2", "ok", "date");
		page.add(table, "newline,grow,push");
		// f.addButton(new JButton(new AbstractAction("清空数据"){
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// model.clear();
		// table.setRow(model);
		// }
		//            
		// }));
		// f.addButton(new JButton(new AbstractAction("增加数据"){
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// model.add(new Student());
		// table.setRow(model);
		// // for(Student s:model){
		// // System.out.println(s.id);
		// // }
		// table.setEditable(1,2,3);
		// }
		//            
		// }));
		// f.showInDialog();
		page.showInFrame();

	}

	public static void ognl() {
		HPage button = new HPage();
		Object value = OgnlUtils.getValue(button, "titlePanel");
		System.out.println(value);
		value = OgnlUtils.getValue(button, "menuBar");// wrong
		System.out.println(value);
	}

	public static void tree() {
		HPage f = new HPage();
		// f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		HPanel form = new HPanel();
		form.add(new HText("hello", "欢乐谷"));
		form.add(new HText("o", "o:"));
		form.add(new HText("hut", "hut:"), true);
		form.addLabel("helllllllllll");
		form.add(new JSpinner(), "");
		form.addFill(new HText("hut:", "haha"), true);
		form.addFill(new HText("hut:", "long"), true);
		f.add(form, "grow");
		List list = new ArrayList();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		HCheckBoxGroup checkBox = new HCheckBoxGroup(list, "欢迎光临");
		// f.add(checkBox,"newline,grow,pushx,pushy");
		// f.setSize(500, 480);
		// f.showErrorMessage("wrong");
		TextValidationList c = new TextValidationList();
		c.addChecker("int", "min=5;typeError=不是数字;rangeError=需大于5");
		JTextField field = new JTextField(10);
		f.add(field, "newline,grow,push");
		HSwingUtils.bindChecker(field, "数字", c);

		final List model = new ArrayList();
		for (int i = 0; i < 100; i++) {
			Vector v = new Vector();
			v.add("A");
			v.add("B");
			v.add("C");
			v.add(new double[] { 1, 2, 3 });
			model.add(v);
		}
		// final String[] model=new String[]{
		// "a","b","c"
		// };
		// final Integer[] model=new Integer[]{
		// 1,2,3
		// };

		final HTree tree = new HTree();
		tree.setData(model);
		f.add(tree, "newline,grow,push");
		f.addButton(new JButton(new AbstractAction("清空数据") {

			@Override
			public void actionPerformed(ActionEvent e) {
			}

		}));
		f.addButton(new JButton(new AbstractAction("增加数据") {

			@Override
			public void actionPerformed(ActionEvent e) {
			}

		}));
		// f.showInDialog();
		f.showInFrame();
	}

	public static void textCheck() {
		final HPage f = new HPage();
		// f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		HPanel form = new HPanel();
		form.setName("hello");
		form.add(new HText("hello", "欢乐谷"));
		form.add(new HText("o", "o:"));
		form.add(new HText("hut", "hut:", true), true);
		form.addLabel("helllllllllll");
		form.add(new JSpinner(), "");
		form.addFill(new HText("hut:", "haha"), true);
		form.addFill(new HText("hut:", "long"), true);
		f.add(form, "grow");
		TextValidationList c = new TextValidationList();
		c.addChecker("int", "min=5;typeError=不是数字;rangeError=需大于5");
		JTextField field = new JTextField(10);
		f.add(field, "newline,grow,push");
		HSwingUtils.bindChecker(field, "数字", c);

		// f.showInDialog();
		ValidationListener listener = new ValidationListener() {

			@Override
			public void stateChanged(ValidationManager vm) {
				f.getTitlePanel().showErrorMessage(vm.getErrorMessage());
			}

		};
		ValidationManager.addValidationListener(f.getName(), listener);

		f.showInFrame();
	}
}

class Student {
	public static int order = 0;

	private String name = "h";
	private Object obj = new JButton();
	public int id = order++;
	public int id2 = id;
	public Date date = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private boolean ok = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

}