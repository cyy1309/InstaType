package model;

import com.sun.tools.internal.ws.processor.model.Request;
import model.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouxiaoyuan on 05/05/2017.
 */
public class ShowAction extends Action {

    public String execute() throws Exception {
        String userID;
        userID = request.getParameter("imgId");

        String image;
        image=request.getParameter("imgUrl");

        request.setAttribute("Id",userID);
        request.setAttribute("image",image);

        Objective.getObjective().getFetched("IMAGE", userID);
        Objective.getObjective().getTextGoogled();
        Objective.getObjective().getTextLeikied();

        String result[][] = Objective.getObjective().textLeikied;

        List<Result> ResultList = new ArrayList<Result>();


        for(int i=0; result[i][0]!=null; i++) {
            Result results = new Result();
            results.setResult1(result[i][0]);
            results.setResult2(result[i][1]);
            ResultList.add(results);
        }


        for(int i=0; result[i][0]!=null; i++) {
            for(int j=0; j<2; j++){
                System.out.println(result[i][j]);
            }
        }

        System.out.println(userID);

        request.setAttribute("ResultList",ResultList);
        request.setAttribute("Result",result);

        return "result.jsp";
    }
}
