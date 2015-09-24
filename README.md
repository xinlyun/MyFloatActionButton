# MyFloatActionButton
根据MD设计方向设计的展示型菜单，上图先
   
![image](https://github.com/xinlyun/MyFloatActionButton/blob/master/sk2.gif)   
代码及实现方案请自行解析，简述用法：
在布局文件中配置

 <com.lin.myfloatactionbtn.MyFloatActionMenu
        android:id="@+id/id_mfam1"
        android:layout_width="wrap_content"
        fabs:mfam_width="400dp"
        fabs:mfam_posi="left_top"
        fabs:mfam_icon="@drawable/ic_fab_star"
        android:layout_height="wrap_content">
        <com.lin.myfloatactionbtn.MyFloatActionButton
            android:layout_width="wrap_content"
            fabs:cir_icon="@drawable/jietu"
            android:layout_height="wrap_content" />

        <com.lin.myfloatactionbtn.MyFloatActionButton
            android:layout_width="wrap_content"
            fabs:cir_icon="@drawable/jietu"
            android:layout_height="wrap_content" />

        <com.lin.myfloatactionbtn.MyFloatActionButton
            android:layout_width="wrap_content"
            fabs:cir_icon="@drawable/jietu"
            android:layout_height="wrap_content" />

        <com.lin.myfloatactionbtn.MyFloatActionButton
            android:layout_width="wrap_content"
            fabs:cir_icon="@drawable/jietu"
            android:layout_height="wrap_content" />
  </com.lin.myfloatactionbtn.MyFloatActionMenu>
需先在主视图中添加属性：
    xmlns:fabs="http://schemas.android.com/apk/res-auto"
目前仅至此添加4个Button，请主动配置四个，不能多不能少。
后期将添加多个Button时的适配.
MyFloatActionMenu
可添加的属性包括：
	mfam_width ：菜单宽度（高度）
        mfam_posi  ：所属位置，属性有	
	 (left_top,left_bottom,right_top,rigth_bottom)这四个
        mfam_icon  ：主按钮图标

MyFloatActionButton
可添加的属性包括：
	cir_icon   ：即按钮图标

2）可为MyFloatActionMenu添加点击事件监听
	menu1.setOnIconClickListener(new IconClickListener() {
            @Override
            public void IconCLick(MyFloatActionButton view, int no) {
              ...
            }
        });
int no :是响应对应FloatButton的位置
当然你也可以对每个MyFloatActionButton设置单独的Click事件监听，不过何必呢？

注意事项：
	1）版本初期内容不完善，连注释基本都没有;
	2）Menu及Button加载按钮图标时需要对图片进行压缩，请量力而行，不要放太大的图标。 
