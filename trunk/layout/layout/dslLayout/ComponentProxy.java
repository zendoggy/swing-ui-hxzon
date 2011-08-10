package layout.dslLayout;

import java.awt.Component;
import java.awt.Container;

public class ComponentProxy {
	public Component self;

	public ComponentProxy() {
	}

	public ComponentProxy(Component self) {
		this.self = self;
	}

	public ComponentProxy at(int x, int y, int width, int height) {
		self.setBounds(x, y, width, height);
		return this;
	}

	public ComponentProxy in(Container parent) {
		parent.setLayout(null);
		parent.add(self);
		return this;
	}

}

