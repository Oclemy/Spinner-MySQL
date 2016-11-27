package com.tutorials.hp.spinnermysql.mMySQL;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.tutorials.hp.spinnermysql.mModel.Spacecraft;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Oclemy on 9/30/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class MySQLClient {

    //SAVE/RETRIEVE URLS
    private static final String DATA_INSERT_URL="http://10.0.2.2/android/GalacticCity/crud.php";
    private static final String DATA_RETRIEVE_URL="http://10.0.2.2/android/GalacticCity/index.php";

    //INSTANCE FIELDS
    private final Context c;
    private ArrayAdapter<Spacecraft> adapter ;

    public MySQLClient(Context c) {
        this.c = c;

    }
    /*
    SAVE/INSERT
     */
    public void add(Spacecraft s, final EditText...editTexts)
    {
        if(s==null)
        {
            Toast.makeText(c, "No Data To Save", Toast.LENGTH_SHORT).show();
        }
            else
        {
            AndroidNetworking.post(DATA_INSERT_URL)
                    .addBodyParameter("action","save")
                    .addBodyParameter("name",s.getName())
                    .addBodyParameter("propellant",s.getPropellant())
                    .addBodyParameter("destination",s.getDestination())
                    .setTag("TAG_ADD")
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {

                            if(response != null)
                                try {
                                    //SHOW RESPONSE FROM SERVER
                                    String responseString = response.get(0).toString();
                                    Toast.makeText(c, "PHP SERVER RESPONSE : " + responseString, Toast.LENGTH_SHORT).show();

                                    if (responseString.equalsIgnoreCase("Success")) {
                                        //CLEAR EDITXTS
                                        EditText nameTxt = editTexts[0];
                                        EditText propTxt = editTexts[1];
                                        EditText destTxt = editTexts[2];

                                        nameTxt.setText("");
                                        propTxt.setText("");
                                        destTxt.setText("");
                                    }else
                                    {
                                        Toast.makeText(c, "PHP WASN'T SUCCESSFUL. ", Toast.LENGTH_SHORT).show();

                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIVED : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                        }

                        //ERROR
                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    });

        }
    }

    /*
    RETRIEVE/SELECT/REFRESH
     */
    public void retrieve(final Spinner sp)
    {
        final ArrayList<Spacecraft> spacecrafts = new ArrayList<>();

        AndroidNetworking.get(DATA_RETRIEVE_URL)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jo;
                        Spacecraft s;
                        try
                        {
                            for(int i=0;i<response.length();i++)
                            {
                                jo=response.getJSONObject(i);

                                int id=jo.getInt("id");
                                String name=jo.getString("name");
                                String propellant=jo.getString("propellant");
                                String destination=jo.getString("destination");

                                s=new Spacecraft();
                                s.setId(id);
                                s.setName(name);
                                s.setPropellant(propellant);
                                s.setDestination(destination);

                                spacecrafts.add(s);
                            }

                            //SET TO SPINNER
                            adapter =new ArrayAdapter(c,android.R.layout.simple_list_item_1,spacecrafts);
                            sp.setAdapter(adapter);

                        }catch (JSONException e)
                        {
                            Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIEVED. "+e.getMessage(), Toast.LENGTH_LONG).show();

                        }

                    }

                    //ERROR
                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_LONG).show();

                    }


                });

    }



}
