import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;


public class VPrules {

	public int stnsnum;
	public Sentence stns;
	public String VP = "VP";
	public String PP = "PP";
	public HashSet <String> verbs;
	
	VPrules(int num, String string) throws IOException {
		stnsnum = num;
		stns = new Sentence(stnsnum, string);
		String elements[] = {"VP", "VB", "VBD", "VBG", "VBN", "VBP", "VBZ"};
		verbs = new HashSet<String>(Arrays.asList(elements));
		//System.out.println("VPrules built. String: "+ str);
	}
	
	
	public void VPrulesfunc() throws IOException {
		int VPchild = existvb();
		int PPchild = exist(PP);
		if (VPchild > 0 && PPchild > VPchild ) {
			stns.swap(VPchild, PPchild);
			//System.out.println("VP:PP rule applied");
		}
	}
	
	public int exist(String label) {
		int j = 1;
		while (true) {
			if (stns.childrange(0, j) != null) {
				if (stns.childlabel(0, j).equals(label))
					return j;
			}
			else
				return -1;
			++j;
		}
	}
	
	public int existvb() {
		int v;
		for(String tag: verbs) {
			if ((v = exist(tag)) > 0) {
				return v;
			}
		}
		return -1;
	}
}
