package cn.growm.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.song.http.QSHttp;
import org.song.http.framework.HttpCallback;
import org.song.http.framework.HttpException;
import org.song.http.framework.QSHttpCallback;
import org.song.http.framework.ResponseParams;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.添加网络框架插件
        //2.创建布局 添加事件
        //3.测试api 调用框架
        //4.获取控件文本 调用api 返回显示到控件
        //模拟器输入不了中文 复制粘贴过去 北京 上海
        final EditText editText=findViewById(R.id.editText);
        Button button=findViewById(R.id.button);
        final TextView textView=findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.help.bj.cn/apis/weather2d/";
                QSHttp.get(url)
                        .param("id", editText.getText().toString())
                        .buildAndExecute(new QSHttpCallback<Root>() {
                            @Override
                            public void onComplete(Root data) {
                                textView.setText(data.getCity()+"今日天气:"+data.getWeather()+"，明日天气:"+data.getTomorrow().getWeather());
                                Log.d("输出图片路径1", "onComplete: "+"https://"+data.getWeatherimg());
                                Log.d("输出图片路径2", "onComplete: "+"https://"+data.getTomorrow().getWeatherimg());
                                showImg("https://"+data.getWeatherimg(),"https://"+data.getTomorrow().getWeatherimg());//调用showImg方法显示图片
                            }
                        });
            }
        });
    }
    public  void showImg(String p1,String p2){
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        Glide.with(this).load(p1).into(imageView2);//调用框架Glide
        Glide.with(this).load(p2).into(imageView3);//这句话的意思是 获取p2链接上的图片  传入imageView3
    }
}

class Tomorrow
{
    private String temp;

    private String weather;

    private String wind;

    private String weatherimg;

    public void setTemp(String temp){
        this.temp = temp;
    }
    public String getTemp(){
        return this.temp;
    }
    public void setWeather(String weather){
        this.weather = weather;
    }
    public String getWeather(){
        return this.weather;
    }
    public void setWind(String wind){
        this.wind = wind;
    }
    public String getWind(){
        return this.wind;
    }
    public void setWeatherimg(String weatherimg){
        this.weatherimg = weatherimg;
    }
    public String getWeatherimg(){
        return this.weatherimg;
    }
}


class Root
{
    private String status;

    private String city;

    private String aqi;

    private String pm25;

    private String temp;

    private String weather;

    private String wind;

    private String weatherimg;

    private Tomorrow tomorrow;

    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return this.city;
    }
    public void setAqi(String aqi){
        this.aqi = aqi;
    }
    public String getAqi(){
        return this.aqi;
    }
    public void setPm25(String pm25){
        this.pm25 = pm25;
    }
    public String getPm25(){
        return this.pm25;
    }
    public void setTemp(String temp){
        this.temp = temp;
    }
    public String getTemp(){
        return this.temp;
    }
    public void setWeather(String weather){
        this.weather = weather;
    }
    public String getWeather(){
        return this.weather;
    }
    public void setWind(String wind){
        this.wind = wind;
    }
    public String getWind(){
        return this.wind;
    }
    public void setWeatherimg(String weatherimg){
        this.weatherimg = weatherimg;
    }
    public String getWeatherimg(){
        return this.weatherimg;
    }
    public void setTomorrow(Tomorrow tomorrow){
        this.tomorrow = tomorrow;
    }
    public Tomorrow getTomorrow(){
        return this.tomorrow;
    }
}
