(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4e6300e0"],{"8f3f":function(t,n,a){"use strict";var e=a("a9c6"),i=a.n(e);i.a},a9c6:function(t,n,a){},b11e:function(t,n,a){"use strict";a.r(n);var e=function(){var t=this,n=t.$createElement,a=t._self._c||n;return a("a-card",{attrs:{bordered:!1}},[a("div",{staticStyle:{width:"100%",height:"100%","min-height":"500px"},attrs:{id:"container"}}),a("div",{staticClass:"input-card"},[a("h4",[t._v("轨迹回放控制")]),a("div",{staticClass:"input-item"},[a("input",{staticClass:"btn",attrs:{type:"button",value:"开始动画",id:"start"},on:{click:function(n){return t.startAnimation()}}}),a("input",{staticClass:"btn",attrs:{type:"button",value:"暂停动画",id:"pause"},on:{click:function(n){return t.pauseAnimation()}}})]),a("div",{staticClass:"input-item"},[a("input",{staticClass:"btn",attrs:{type:"button",value:"继续动画",id:"resume"},on:{click:function(n){return t.resumeAnimation()}}}),a("input",{staticClass:"btn",attrs:{type:"button",value:"停止动画",id:"stop"},on:{click:function(n){return t.stopAnimation()}}})])])])},i=[],o={components:{},name:"ReplayHis",data:function(){return{marker:null,lineArr:[]}},mounted:function(){this.amapView()},methods:{amapView:function(){var t=this;AMap.plugin("AMap.MoveAnimation",(function(){var n,a=[[116.478935,39.997761],[116.478939,39.997825],[116.478912,39.998549],[116.478912,39.998549],[116.478998,39.998555],[116.478998,39.998555],[116.479282,39.99856],[116.479658,39.998528],[116.480151,39.998453],[116.480784,39.998302],[116.480784,39.998302],[116.481149,39.998184],[116.481573,39.997997],[116.481863,39.997846],[116.482072,39.997718],[116.482362,39.997718],[116.483633,39.998935],[116.48367,39.998968],[116.484648,39.999861]],e=new AMap.Map("container",{resizeEnable:!0,zoom:17});n=new AMap.Marker({map:e,position:[116.478935,39.997761],icon:"https://a.amap.com/jsapi_demos/static/demo-center-v2/car.png",offset:new AMap.Pixel(-13,-26)});new AMap.Polyline({map:e,path:a,showDir:!0,strokeColor:"#28F",strokeWeight:6});var i=new AMap.Polyline({map:e,strokeColor:"#AF5",strokeWeight:6});n.on("moving",(function(t){i.setPath(t.passedPath)})),t.lineArr=a,t.marker=n,e.setFitView()}))},amapView14:function(){var t,n=[[116.478935,39.997761],[116.478939,39.997825],[116.478912,39.998549],[116.478912,39.998549],[116.478998,39.998555],[116.478998,39.998555],[116.479282,39.99856],[116.479658,39.998528],[116.480151,39.998453],[116.480784,39.998302],[116.480784,39.998302],[116.481149,39.998184],[116.481573,39.997997],[116.481863,39.997846],[116.482072,39.997718],[116.482362,39.997718],[116.483633,39.998935],[116.48367,39.998968],[116.484648,39.999861]],a=new AMap.Map("container",{resizeEnable:!0,center:[116.397428,39.90923],zoom:17});t=new AMap.Marker({map:a,position:[116.478935,39.997761],icon:"https://webapi.amap.com/images/car.png",offset:new AMap.Pixel(-26,-13),autoRotation:!0,angle:-90});new AMap.Polyline({map:a,path:n,showDir:!0,strokeColor:"#28F",strokeWeight:6});var e=new AMap.Polyline({map:a,strokeColor:"#AF5",strokeWeight:6});t.on("moving",(function(t){e.setPath(t.passedPath)})),this.lineArr=n,this.marker=t,a.setFitView()},startAnimation:function(){var t=this.lineArr;this.marker.moveAlong(t,{duration:200,autoRotation:!0})},pauseAnimation:function(){this.marker.pauseMove()},resumeAnimation:function(){this.marker.resumeMove()},stopAnimation:function(){this.marker.stopMove()}}},s=o,r=(a("8f3f"),a("4023")),p=Object(r["a"])(s,e,i,!1,null,null,null);n["default"]=p.exports}}]);