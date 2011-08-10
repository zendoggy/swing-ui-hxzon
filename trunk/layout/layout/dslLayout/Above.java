package layout.dslLayout;

import java.awt.Component;
import java.awt.Container;

public class Above extends ComponentProxy {
	private ComponentProxy up, low;
	private double upRatio;

	public Above(Component up, Component low, double upRatio) {
		this(new ComponentProxy(up), new ComponentProxy(low), upRatio);
	}

	public Above(ComponentProxy up, ComponentProxy low, double upRatio) {
		this.up = up;
		this.low = low;
		this.upRatio = upRatio;
	}

	public ComponentProxy at(int x, int y, int width, int height) {
		up.at(x, y, width, (int) (height * upRatio));
		low.at(x, (int) (y + height * upRatio), width, (int) (height * (1 - upRatio)));
		return this;
	}

	public ComponentProxy in(Container parent) {
		parent.setLayout(null);
		up.in(parent);
		low.in(parent);
		return this;
	}
}
