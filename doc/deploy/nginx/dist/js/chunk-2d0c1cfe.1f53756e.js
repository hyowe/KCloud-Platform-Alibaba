(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0c1cfe"],{4848:function(e,r,t){"use strict";t.r(r);var s=function(){var e=this,r=e.$createElement,t=e._self._c||r;return t("div",{staticClass:"table-page-search-wrapper"},[t("div",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["sys:search:resource:query"],expression:"['sys:search:resource:query']"}]},[t("a-input",{attrs:{placeholder:"请输入搜索内容","allow-clear":""},on:{keyup:function(r){return!r.type.indexOf("key")&&e._k(r.keyCode,"enter",13,r.key,"Enter")?null:e.searchResource()}},model:{value:e.keyword,callback:function(r){e.keyword=r},expression:"keyword"}},[t("a-icon",{style:{color:"rgba(0,0,0,.25)"},attrs:{slot:"prefix",type:"search"},slot:"prefix"})],1)],1),t("br"),""!=e.searchWord?t("div",[t("span",{staticStyle:{"font-size":"14px","font-weight":"bolder"}},[e._v("搜索 "),t("span",{staticStyle:{color:"red"}},[e._v(e._s(e.searchWord))]),e._v(" 找到 "),t("span",{staticStyle:{color:"red"}},[e._v(e._s(e.total))]),e._v(" 个相关内容")])]):e._e(),t("br"),e._l(e.list,(function(r,s){return t("div",{key:s},[t("div",{staticStyle:{"font-size":"14px","font-weight":"bolder"},domProps:{innerHTML:e._s(r.title)}}),t("span",{staticStyle:{"font-size":"14px"},domProps:{innerHTML:e._s(r.remark)}}),"audio"==r.code?t("router-link",{staticStyle:{color:"dodgerblue"},attrs:{to:{name:"sysresourceaudioview",query:{id:r.id}}}},[e._v(" 查看详情")]):e._e(),"image"==r.code?t("router-link",{staticStyle:{color:"dodgerblue"},attrs:{to:{name:"sysresourceimageview",query:{id:r.id}}}},[e._v(" 查看详情")]):e._e(),"video"==r.code?t("router-link",{staticStyle:{color:"dodgerblue"},attrs:{to:{name:"sysresourcevideoview",query:{id:r.id}}}},[e._v(" 查看详情")]):e._e(),t("br"),t("br")],1)}))],2)},o=[],i=t("b775");function a(e){return Object(i["b"])({url:"/admin/sys/search/api/resource",method:"post",data:e})}var n={name:"SearchResource",data:function(){return{keyword:"",total:0,searchWord:"",form:{pageNum:1,pageSize:1e4,needPage:!0,orSearchList:[],highlightFieldList:["title","remark"],queryStringList:[],indexNames:["laokou_resource"]},list:[]}},filters:{},created:function(){},computed:{},watch:{},methods:{searchResource:function(){var e=this,r=this.keyword;null!=r&&""!==r&&void 0!==r?(this.form.queryStringList=[],this.form.queryStringList.push({field:"remark",value:this.keyword}),this.form.queryStringList.push({field:"title",value:this.keyword}),a(this.form).then((function(t){e.searchWord=r,e.list=null==t.data?[]:t.data.records,e.total=null==t.data?0:t.data.total-0}))):this.$message.error("请输入关键字",3)}}},l=n,c=t("2877"),d=Object(c["a"])(l,s,o,!1,null,"25efffe8",null);r["default"]=d.exports}}]);