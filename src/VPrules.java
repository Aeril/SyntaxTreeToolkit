import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;


public class VPrules {

	public int stnsnum;
	public String str;
	public Sentence stns;
	public String VP = "VP";
	public String PP = "PP";
	public HashSet <String> verbs; 
	FileWriter flog;
	
	VPrules(int num, String string) throws IOException {
		stnsnum = num;
		str = string;
		stns = new Sentence(stnsnum, str);
		String elements[] = {"VP", "VB", "VBD", "VBG", "VBN", "VBP", "VBZ"};
		verbs = new HashSet<String>(Arrays.asList(elements));
		//System.out.println("VPrules built. String: "+ str);
		flog = new FileWriter("change.log", true);
	}
	
	
	public void VPrulesfunc() throws IOException {
		int VPchild = existvb();
		int PPchild = exist(PP);
		if (VPchild > 0 && PPchild > VPchild ) {
			swap(VPchild, PPchild);
			//System.out.println("VP:PP rule applied");
		}
		flog.close();
	
	/*	int left = 0;
		int i = 0;
		int right;
		String child;
		while((i = (stns.indexOf(VP, i) + 1)) > 0) { 
			left = i - 1;
			right = match(stns, left);
			System.out.println(left+" "+right);
			child = child(stns, left, 1);
			System.out.println("child 1: "+child);
			// if(children(stns))
			// swap(stns, left);
		}*/
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
	
	public void swap(int a, int b) throws IOException {
		int [] range1 = stns.childrange(0, a);
		int [] range2 = stns.childrange(0, b);
		String substring1 = str.substring(range1[0],range1[1]+1);
		String substring2 = str.substring(range2[0],range2[1]+1);
		flog.write("Swap:\n" + str.substring(range1[0], range1[1]+1) + "\nAnd:\n" + str.substring(range2[0], range2[1]+1) + "\n\n");
		//str = buf.toString();
		str = str.substring(0, range1[0]) + substring2 + str.substring(range1[1], range2[0]) + substring1 + str.substring(range2[1]);
		stns.setstns(str);
		
	}
	
}
