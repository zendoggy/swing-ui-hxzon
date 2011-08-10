package layout.dslLayout;

import java.awt.Container;

public class Empty extends ComponentProxy {
	public ComponentProxy at(int x, int y, int width, int height) {
		return this;
	}

	public ComponentProxy in(Container parent) {
		return this;
	}
}
