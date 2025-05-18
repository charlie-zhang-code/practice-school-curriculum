Page({
    data: {
      id1: "back",
      id2: "clear",
      id3: "negative",
      id4: "÷",
      id5: "7",
      id6: "8",
      id7: "9",
      id8: "×",
      id9: "4",
      id10: "5",
      id11: "6",
      id12: "-",
      id13: "1",
      id14: "2",
      id15: "3",
      id16: "+",
      id17: "0",
      id18: ".",
      id19: "=",
      screenData: "0", //屏幕数字初始化
      lastInput: false, //控制最后的输入
      array: [], //定义一个空数组
    },
    onclickButton: function(e) {
      var id = e.target.id;
      if (id == this.data.id1) { //退格
        var data = this.data.screenData;
        if (data == 0) {
          return;
        }
        data = data.substring(0, data.length - 1); //删除最后一位
        if (data == "" || data == "-") { //如果为空或者符号置零
          data = 0;
        }
        this.setData({
          screenData: data
        });
        this.data.array.pop();
        this.data.array.push(data);
      } else if (id == this.data.id2) { //清屏
        this.setData({
          screenData: "0"
        });
        this.data.array.length = 0;
      } else if (id == this.data.id3) { //正负号
        var data = this.data.screenData;
        if (data == 0) {
          return;
        }
        var firstWord = data.substring(0, 1);
        if (firstWord == "-") {
          data = data.substring(1, data.length); //去掉负号
          this.data.array.shift();
        } else {
          data = "-" + data;
          this.data.array.unshift("-"); //增加负号
        }
        this.setData({
          screenData: data
        });
  
      } else if (id == this.data.id19) { // =
        var data = this.data.screenData;
        if (data == 0) {
          return;
        }
        var lastWord = data.substring(data.length - 1, data.length);
        if (isNaN(lastWord)) {
          return; //最后一位不是数字返回
        }
        var num = ""; //定义一个字符串
        var lastInput;
        var array = this.data.array;
        var optaration = [];
        for (var i in array) {
          if (isNaN(array[i]) == false || array[i] == this.data.id18 || array[i] == this.data.id3) {
            num += array[i]; //是小数点或者正负号进行合并处理
          } else { //加减乘除单独处理
            lastInput = array[i];
            optaration.push(num);
            optaration.push(array[i]);
            num = "";
          }
        }
        optaration.push(Number(num));
        var result = Number(optaration[0]) * 1.0;
        for (var i = 1; i < optaration.length; i++) {
          if (isNaN(optaration[i])) {
            if (optaration[1] == this.data.id4) { //除运算
              result /= Number(optaration[i + 1]);
            } else if (optaration[1] == this.data.id8) { //乘运算
              result *= Number(optaration[i + 1]);
            } else if (optaration[1] == this.data.id12) { //减运算
              result -= Number(optaration[i + 1]);
            } else if (optaration[1] == this.data.id16) { //加运算
              result += Number(optaration[i + 1]);
            }
          }
        }
        this.data.array.length = 0;
        this.data.array.push(result);
        this.setData({
          screenData: result + "" //将计算结果赋值用于在屏幕上显示
        });
      } else {
        if (id == this.data.id4 || id == this.data.id8 || id == this.data.id12 || id == this.data.id16) {
          if (this.data.lastInput == true || this.data.screenData == 0) {
            return; //开始不允许输入加减乘除
          }
        }
        var vd = this.data.screenData;
        var data;
        if (vd == 0) {
          data = id;
        } else {
          data = vd + id; //用于连续输入数字
        }
        this.setData({
          screenData: data
        });
        this.data.array.push(id);
        if (id == this.data.id4 || id == this.data.id8 || id == this.data.id12 || id == this.data.id16) {
          this.setData({
            lastInput: true
          });
        } else {
          this.setData({
            lastInput: false
          });
        }
      }
    }
  })