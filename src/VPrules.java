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
		//System.out.println("VPrules built. String: "+ string);
	}
	
	
	public void VPrulesfunc() throws IOException {
		/*if (stns.last(VP) < 0) {
			System.out.println("Wrong reference in VPrules.VPrulesfunc()\n");
			System.exit(0);
		}*/
		int VPchild = existvb();
		int PPchild = stns.exist(PP);
		if (VPchild > 0 && PPchild > VPchild ) {
			stns.swap(VPchild, PPchild);
			//System.out.println("VP:PP rule applied");
		}
	}
	

	
	public int existvb() {
		int v;
		for(String tag: verbs) {
			if ((v = stns.exist(tag)) > 0) {
				return v;
			}
		}
		return -1;
	}
}
