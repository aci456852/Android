package com.example.administrator.highlevel;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class FragmentCustomservices extends AppCompatActivity {

    private static final String API_URL="http://www.tuling123.com/openapi/api";
    private static final String KEY="a8541059501340bca1ba35e79ec12db5";
    private MessageAdapter messageAdapter;
    private int id=1;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_customservices);
        messageAdapter=new MessageAdapter();
        listView = ((ListView) findViewById(R.id.listView));
        listView.setAdapter(messageAdapter);
        listView.setDividerHeight(0);

    }
    public void handleSendMessage(View view){
        final ChatMessage chatMessage=new ChatMessage();
        chatMessage.setId(id);
        id=id+1;
        chatMessage.setName("lzy");
        chatMessage.setPlace(2);
        String words= ((EditText) findViewById(R.id.editText)).getText().toString();
        chatMessage.setWords(words);

        messageAdapter.addMessage(chatMessage);
        messageAdapter.notifyDataSetChanged();
        listView.setSelection(messageAdapter.getCount()-1);

        AsyncHttpClient httpClient=new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.setUseJsonStreamer(true);
        params.put("key",KEY);
        params.put("info",chatMessage.getWords());
        params.put("userid",chatMessage.getName());

        httpClient.post(API_URL,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    Log.i("AAA",response.getString("text"));
                    ChatMessage chatMessage=new ChatMessage();
                    chatMessage.setId(id);
                    id=id+1;
                    chatMessage.setName("西湖小导游");
                    chatMessage.setPlace(1);
                    chatMessage.setWords(response.getString("text"));

                    messageAdapter.addMessage(chatMessage);
                    messageAdapter.notifyDataSetChanged();
                    listView.setSelection(messageAdapter.getCount()-1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private class MessageAdapter extends BaseAdapter {
        private List<ChatMessage> messageList=new ArrayList<>();

        private void addMessage(ChatMessage chatMessage){
            messageList.add(chatMessage);
        }
        @Override
        public int getCount() {
            return messageList.size();
        }

        @Override
        public Object getItem(int position) {
            return messageList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return messageList.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ChatMessage message=messageList.get(position);
            if(message.getPlace()==1)
                convertView=View.inflate(FragmentCustomservices.this,R.layout.message_left,null);
            else
                convertView=View.inflate(FragmentCustomservices.this,R.layout.message_right,null);
            ((TextView) convertView.findViewById(R.id.words)).setText(message.getWords());
            return convertView;
        }
    }

}
