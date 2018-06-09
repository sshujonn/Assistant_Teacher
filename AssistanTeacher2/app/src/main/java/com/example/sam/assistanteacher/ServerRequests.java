package com.example.sam.assistanteacher;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


import java.util.ArrayList;

/**
 * Created by ASUS on 25-Mar-16.
 */
public class ServerRequests {

    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://rebelfreaks.net/AssistanTeacher";
    //public static final String SERVER_ADDRESS ="C://xampp/htdocs";

    public ServerRequests(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait...");
    }

    public void storeUserDataInBackground(User user, GetUserCallback userCallback) {
        progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallback).execute();

    }

    public void fetchUserDataInBackground(User user, GetUserCallback callback) {
        progressDialog.show();
        new fetchUserDataAsyncTask(user,callback).execute();
    }

    ////////////////////////////////////////////////////////////
    public void storeCourseDataInBackground(userAddCourse userAddCourse, GetCourseCallback courseCallback) {
        progressDialog.show();
        new StoreCourseDataAsyncTask(userAddCourse , courseCallback).execute();
    }
    public void fetchCourseDataInBackground(userAddCourse userAddCourse, GetCourseCallback courseCallback) {
        progressDialog.show();
        new fetchCourseDataAsyncTask(userAddCourse, courseCallback).execute();
    }

    private class StoreCourseDataAsyncTask extends AsyncTask<Void, Void, Void> {

        userAddCourse userAddCourse;
        GetCourseCallback courseCallback;
        public StoreCourseDataAsyncTask(userAddCourse userAddCourse, GetCourseCallback courseCallback) {

            this.userAddCourse = userAddCourse;
            this.courseCallback = courseCallback;

        }

        @Override
        protected Void doInBackground(Void... params) {


            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("courseName", userAddCourse.CourseName));
            dataToSend.add(new BasicNameValuePair("prefix", userAddCourse.Prefix));
            dataToSend.add(new BasicNameValuePair("courseNo", userAddCourse.CourseNo));
            dataToSend.add(new BasicNameValuePair("postfix", userAddCourse.Postfix));
            dataToSend.add(new BasicNameValuePair("credit", userAddCourse.Credit));
            dataToSend.add(new BasicNameValuePair("session", userAddCourse.Session));
            dataToSend.add(new BasicNameValuePair("degree", userAddCourse.Degree));
            dataToSend.add(new BasicNameValuePair("year", userAddCourse.Year));
            dataToSend.add(new BasicNameValuePair("term", userAddCourse.Term));


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "related course php file here");/////////////////php is not input


            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            courseCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }

    public class fetchCourseDataAsyncTask extends AsyncTask<Void, Void, userAddCourse> {

        userAddCourse userAddCourse;
        GetCourseCallback courseCallback;

        public fetchCourseDataAsyncTask(userAddCourse userAddcourse, GetCourseCallback courseCallback) {
            this.userAddCourse = userAddcourse;
            this.courseCallback = courseCallback;

        }
        @Override
        protected userAddCourse doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("courseName", userAddCourse.CourseName));


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
       /**/     HttpPost post = new HttpPost(SERVER_ADDRESS + "/Login_info.php");

            com.example.sam.assistanteacher.userAddCourse returnCourse = null;
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jObject = new JSONObject(result);

                if (jObject.length() == 0) {
                    returnCourse = null;
                } else {
                    String CourseName = jObject.getString("courseName");
                    //returnedUser = new User(name, user.username, user.password,user.university,user.department,user.sex,user.designation);
                    returnCourse=new userAddCourse(CourseName,userAddCourse.Prefix,userAddCourse.CourseNo,userAddCourse.Postfix,userAddCourse.Credit,userAddCourse.Session,userAddCourse.Degree,userAddCourse.Year,userAddCourse.Term);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return returnCourse;
        }

        @Override
        protected void onPostExecute(userAddCourse returnCourse) {
            progressDialog.dismiss();
            courseCallback.done(returnCourse);
            super.onPostExecute(returnCourse);
        }
    }

    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void> {

        User user;
        GetUserCallback userCallback;

        public StoreUserDataAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;

        }

        @Override
        protected Void doInBackground(Void... params) {


            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("name", user.name));
            dataToSend.add(new BasicNameValuePair("username", user.username));
            dataToSend.add(new BasicNameValuePair("password", user.password));
            dataToSend.add(new BasicNameValuePair("university", user.university));
            dataToSend.add(new BasicNameValuePair("department", user.department));
            dataToSend.add(new BasicNameValuePair("sex", user.sex));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "/Register_info1.php");


            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            userCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }
    public class fetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {

        User user;
        GetUserCallback userCallback;

        public fetchUserDataAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;

        }
        @Override
        protected User doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("username", user.username));
            dataToSend.add(new BasicNameValuePair("password", user.password));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
       /**/     HttpPost post = new HttpPost(SERVER_ADDRESS + "/Login_info.php");

            User returnedUser = null;
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jObject = new JSONObject(result);

                if (jObject.length() == 0) {
                    returnedUser = null;
                } else {
                    String name = jObject.getString("name");
                    returnedUser = new User(name, user.username, user.password,user.university,user.department,user.sex,user.designation);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return returnedUser;
        }

        @Override
        protected void onPostExecute(User returnedUser) {
            progressDialog.dismiss();
            userCallback.done(returnedUser);
            super.onPostExecute(returnedUser);
        }
    }


}
