package layout.dslLayout;

public class ComponentProxyFactory {
	public static ComponentProxy center(ComponentProxy cp, double hRatio, double vRatio) {
		double s1 = (1 - 2.0 * hRatio) / (1.0 - hRatio);
		double s2 = (1 - 2.0 * vRatio) / (1.0 - vRatio);
		ComponentProxy u = new Above(new Empty(), new Above(cp, new Empty(), s2), vRatio);
		return new Beside(new Empty(), new Beside(u, new Empty(), s1), hRatio);
	}

	private static ComponentProxy hSeq(ComponentProxy[] cps, int index, int num) {
		if (num == 1){
			return cps[index];
		}
		return new Beside(cps[index], hSeq(cps, index + 1, num - 1), 1.0 / num);
	}

	public static ComponentProxy hSeq(ComponentProxy[] cps) {
		return hSeq(cps, 0, cps.length);
	}

	private static ComponentProxy vSeq(ComponentProxy[] cps, int index, int num) {
		if (num == 1){
			return cps[index];
		}
		return new Above(cps[index], vSeq(cps, index + 1, num - 1), 1.0 / num);
	}

	public static ComponentProxy vSeq(ComponentProxy[] cps) {
		return vSeq(cps, 0, cps.length);
	}

	public static ComponentProxy block(ComponentProxy[] cps, int N, int M) {
		ComponentProxy[][] fcps = formalize(cps, N, M);
		ComponentProxy[] rows = new ComponentProxy[fcps.length];
		for (int i = 0; i < fcps.length; i++) {
			rows[i] = hSeq(fcps[i]);
		}
		return vSeq(rows);
	}

	private static ComponentProxy[][] formalize(ComponentProxy[] cps, int N, int M) {
		ComponentProxy[][] fcps = new ComponentProxy[N][M];
		for (int ni = 0, i = 0; ni < N; ni++)
			for (int mi = 0; mi < M; mi++, i++) {
				if (i < cps.length) {
					fcps[ni][mi] = cps[i];
				} else{
					fcps[ni][mi] = new Empty();
				}
			}
		return fcps;
	}

	public static ComponentProxy blockWithMargin(ComponentProxy[] cps, int N, int M, double hRatio, double vRatio) {
		ComponentProxy[] ncps = new ComponentProxy[cps.length];
		for (int i = 0; i < cps.length; i++) {
			ncps[i] = center(cps[i], hRatio, vRatio);
		}
		return block(ncps, N, M);
	}
}
