package model;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Objective extends Action {

	final int USERSIMILARITYNUM = 48;
	private static Objective objInstance;

	static String[][] usersFetched;
	static String[][] imagesFetched;
	static String[] textGoogled;
	static String[][] textLeikied;

	//运用单例模式，私有地初始化（只会被执行一次
	public Objective() {}

	//得到这个实例，如果不存在就初始化（仅有这一次），以后如果判定存在，就返回这一实例
	//此后每次想得到这个人的资料，都要调用 Objetcive.getObjective方法，只有这个方法能访问到这个实例
	public static Objective getObjective() {
		if (objInstance == null) {
			synchronized (Objective.class) {
				if (objInstance == null) {
					objInstance = new Objective();
				}
			}
		}

		return objInstance;
	}

	/*在这个方法里设置这一实例的token，如果要分析的目标改变，也不实例化新对象，只是改变对象的token
	public static void setObjective(String id) throws IOException {
		Objective obj = (Objective) getObjective();
		if (obj.id != id) {
			obj.id = id;
		}
	}*/

	//调用InstagramFecther的方法，将fetcher得到的imagesURL存入imagesFetched
	public String[][] getFetched(String choice, String idOrName) throws IOException {
		InstagramFetcher instagramFc = Factory.generateInstsgramFetcher();
		switch(choice) {
			case "USER" :
				usersFetched = getSpectificNumUsersFetched(instagramFc.getFetched("USER", idOrName));
				return usersFetched;
			case "IMAGE" :
				imagesFetched = instagramFc.getFetched("IMAGE", idOrName);
				return imagesFetched;
			default:
				return null;
		}
	}

	//遍历image数组，每张图都调用方法去google，并解析结果存入textGoogled
	public String[] getTextGoogled() throws IOException {
		ImageAnalyst imageAs = Factory.generateImageAnalyst();
		textGoogled = imageAs.analyseImages(imagesFetched);
		return textGoogled;
	}

	//将textGoogled数组的调用方法去leiki，并存入jsonLeikied @陈勇毅
	public String[][] getTextLeikied() throws IOException {
		TextMiner textMn = Factory.generateTextMiner();
		textLeikied = textMn.mineText(textGoogled);
		return textLeikied;
	}

	public String execute() throws Exception{
		String userName;
		userName = request.getParameter("userName");
		getObjective().getFetched("USER",userName);

		List<String> imgUrl = new ArrayList<String>();
		for(int i=0;i<usersFetched.length;i++){
			imgUrl.add(usersFetched[i][2]);
		}

		List<String> imgId = new ArrayList<String>();
		for(int j=0;j<usersFetched.length;j++){
			imgId.add(usersFetched[j][0]);
		}

		List<String> usrId = new ArrayList<String>();
		for(int k=0;k<usersFetched.length;k++){
			usrId.add(usersFetched[k][1]);
		}

		request.setAttribute("imgUrl",imgUrl);
		request.setAttribute("imgId",imgId);
		request.setAttribute("usrId",usrId);

		return "index.jsp";
	}

	public String[][] getSpectificNumUsersFetched(String[][] generalUsersFetched) {
		int usersNum=0;
		while (generalUsersFetched[usersNum][0]!=null && usersNum<USERSIMILARITYNUM){
			usersNum++;
		}
		String[][] spectificNumUsersFetched = new String[usersNum][3];
		for (int i=0; i<usersNum; i++) {
			spectificNumUsersFetched[i] = generalUsersFetched[i];
		}
		return spectificNumUsersFetched;
	}
}



