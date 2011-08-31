package org.hxzon.demo.timingframework;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.AnimatorBuilder;
import org.jdesktop.core.animation.timing.PropertySetter;
import org.jdesktop.core.animation.timing.TimingSource;
import org.jdesktop.core.animation.timing.triggers.MouseTriggerEvent;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;
import org.jdesktop.swing.animation.timing.triggers.TriggerUtility;
import org.pushingpixels.trident.Timeline;

public class ButtonFg extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final long duration = 1500;
	private static final String buttonProperty = "background";

	public ButtonFg() {
		this.setLayout(new FlowLayout());
		addButton1();
		final TimingSource ts = new SwingTimerTimingSource();
		AnimatorBuilder.setDefaultTimingSource(ts);
		ts.init();
		addButton2();
		addButton3();
		addButton4();
		//jframe
		this.setSize(400, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void addButton1() {
		JButton button = new JButton("sample trident");
		this.add(button);

		final Timeline rolloverTimeline = new Timeline(button);
		rolloverTimeline.addPropertyToInterpolate(buttonProperty, Color.blue, Color.red);
		rolloverTimeline.setDuration(duration);
		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				rolloverTimeline.play();
			}

			public void mouseExited(MouseEvent e) {
				rolloverTimeline.playReverse();
			}
		});
	}

	private void addButton2() {
		JButton button = new JButton("sample timingframework");
		this.add(button);

		final Animator animator = new AnimatorBuilder().setDuration(duration, TimeUnit.MILLISECONDS).build();
		animator.addTarget(PropertySetter.getTarget(button, buttonProperty, Color.blue, Color.red));
		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				animator.start();
			}

			public void mouseExited(MouseEvent e) {
				//hxzon:which method ?
//				animator.stop();
//				animator.startReverse();
//				animator.reverseNow();
				if (animator.isRunning() && animator.getCurrentDirection() == animator.getStartDirection()) {
					final boolean reverseSucceeded = animator.reverseNow();
					if (reverseSucceeded)
						return;
				}
				animator.stop();
				animator.startReverse();
			}
		});
	}

	private void addButton3() {
		JButton button = new JButton("sample timingframework2");
		this.add(button);

		Animator animator = new AnimatorBuilder().setDuration(duration, TimeUnit.MILLISECONDS).build();
		animator.addTarget(PropertySetter.getTarget(button, buttonProperty, Color.blue, Color.red));
		TriggerUtility.addMouseTrigger(button, animator, MouseTriggerEvent.ENTER);
		animator = new AnimatorBuilder().setDuration(duration, TimeUnit.MILLISECONDS).build();
		animator.addTarget(PropertySetter.getTarget(button, buttonProperty, Color.red, Color.blue));
		TriggerUtility.addMouseTrigger(button, animator, MouseTriggerEvent.EXIT);
	}

	private void addButton4() {
		JButton button = new JButton("sample timingframework3");
		this.add(button);

		Animator animator = new AnimatorBuilder().setDuration(duration, TimeUnit.MILLISECONDS).build();
		//PropertySetter.getTargetTo is wrong
		animator.addTarget(PropertySetter.getTarget(button, buttonProperty, Color.blue, Color.red));
		TriggerUtility.addMouseTrigger(button, animator, MouseTriggerEvent.ENTER, true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ButtonFg().setVisible(true);
			}
		});
	}
}
