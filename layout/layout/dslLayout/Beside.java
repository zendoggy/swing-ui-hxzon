package layout.dslLayout;

import java.awt.Component;
import java.awt.Container;

public class Beside extends ComponentProxy {
	private ComponentProxy left, right;
	private double leftRatio;

	public Beside(Component left, Component right, double leftRatio) {
		this(new ComponentProxy(left), new ComponentProxy(right), leftRatio);
	}

	public Beside(ComponentProxy left, ComponentProxy right, double leftRatio) {
		this.left = left;
		this.right = right;
		this.leftRatio = leftRatio;
	}

	public ComponentProxy at(int x, int y, int width, int height) {
		left.at(x, y, (int) (width * leftRatio), height);
		right.at((int) (x + width * leftRatio), y, (int) (width * (1 - leftRatio)), height);
		return this;
	}

	public ComponentProxy in(Container parent) {
		parent.setLayout(null);
		left.in(parent);
		right.in(parent);
		return this;
	}
}
