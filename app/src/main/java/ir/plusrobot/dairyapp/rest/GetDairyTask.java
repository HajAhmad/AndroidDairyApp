package ir.plusrobot.dairyapp.rest;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Ahmad Reza on 15/01/2018.
 */

public class GetDairyTask extends AsyncTask<String, Void, List<Dairy>>{

    private Context mContext;
    private List<Dairy> mDairyList;

    public GetDairyTask(Context context){
        this.mContext= context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Dairy> doInBackground(String... params) {

        String data = ((new RestConfig()).getData(params[0]));

        return null;
    }


    @Override
    protected void onPostExecute(List<Dairy> dairies) {
        super.onPostExecute(dairies);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Toast.makeText(mContext, "دریافت اطلاعات لغو شد", Toast.LENGTH_SHORT).show();
    }


/*    public List<Dairy> getDairyList(){


    }*/

}
