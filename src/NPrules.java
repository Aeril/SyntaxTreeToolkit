import java.io.IOException;


public class NPrules {

	public int stnsnum;
	public Sentence stns;
	public String NP = "NP";
	public String PP = "PP";
	//public HashSet <String> verbs;
	
	NPrules(int num, String string) throws IOException {
		stnsnum = num;
		stns = new Sentence(stnsnum, string);
		
		//String elements[] = {"VP", "VB", "VBD", "VBG", "VBN", "VBP", "VBZ"};
		//verbs = new HashSet<String>(Arrays.asList(elements));
		//System.out.println("NPrules built. String: "+ string);
	}
	
	
	public void NPrulesfunc() throws IOException {
		
		int NPchild = existnp();
		int PPchild = stns.exist(PP);
		int PPlastchild = stns.last(PP);
		/*if (NPchild < 0) {
			System.out.println("Wrong reference in NPrules.NPrulesfunc()\n");
			System.exit(0);
		}*/
		if (NPchild > 0 && PPlastchild > NPchild ) {
			
			while(PPchild < PPlastchild) {
				
				if(stns.childrange(0, PPlastchild) != null) {
					stns.swap(PPchild, PPlastchild);
				}
				++ PPchild;
				-- PPlastchild;
			}
			stns.swap(NPchild, stns.last(PP));
			//System.out.println("NP:PP rule applied");
			
			//System.out.println("VP:PP rule applied");
		}
	}
	

	
	public int existnp() {
		int v;
		if ((v = stns.exist(NP)) > 0) {
			return v;
		}
		return -1;
	}
}
