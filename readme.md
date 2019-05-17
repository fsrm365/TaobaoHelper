## TaobaoHelper

TaobaoHelper 是一个Xposed模块， 由于淘宝业务数据请求有一个签名字段，签名算法还原比较困难，于是直接将签名服务运行在手机上，让签名服务者直接使用网络通信获取。

源码：https://github.com/Poyu222/TaobaoHelper

#### 接口定位

![签名函数](https://github.com/Poyu222/TaobaoHelper/blob/master/docs/calsigin.jpg?raw=true)



#### 网络请求操作

```java
	Socket socket = new Socket("192.168.1.130", 2153);
		JsonObject calSignTask = new JsonObject();
		calSignTask.addProperty("id", 1000);
		calSignTask.addProperty("action", "CalsignAction");
		calSignTask.addProperty("args", new Gson().toJson(params));
		System.out.println("task:" + calSignTask.toString());
		socket.getOutputStream().write(calSignTask.toString().getBytes());
		InputStream is = socket.getInputStream();
		byte[] buffer = new byte[1024];
		int n = is.read(buffer);
		String str = new String(buffer, 0, n);
		return new JsonParser().parse(str).getAsJsonObject().get("ret_msg").getAsString();
```


