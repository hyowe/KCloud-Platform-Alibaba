(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-108003b7"],{"765c":function(e,t,r){"use strict";r.d(t,"k",(function(){return n})),r.d(t,"h",(function(){return i})),r.d(t,"i",(function(){return a})),r.d(t,"b",(function(){return s})),r.d(t,"a",(function(){return u})),r.d(t,"l",(function(){return d})),r.d(t,"o",(function(){return c})),r.d(t,"c",(function(){return l})),r.d(t,"q",(function(){return m})),r.d(t,"e",(function(){return f})),r.d(t,"m",(function(){return p})),r.d(t,"f",(function(){return b})),r.d(t,"d",(function(){return h}));var o=r("b775");function n(e){return Object(o["b"])({url:"/admin/v1/resource/list",method:"post",data:e})}function i(e){return Object(o["b"])({url:"/admin/v1/resource/"+e,method:"get"})}function a(e){return Object(o["b"])({url:"/admin/v1/resource/"+e+"/detail-task",method:"get"})}function s(e,t){return Object(o["b"])({url:"/admin/v1/resource/audit-task",method:"post",data:e,headers:{"Content-Type":"application/json;charset=UTF-8","request-id":t}})}function u(e){return Object(o["b"])({url:"/admin/sys/resource/image/api/insert",method:"post",data:e})}function d(e){return Object(o["b"])({url:"/admin/v1/resource/task-list",method:"post",data:e})}function c(e){return Object(o["b"])({url:"/admin/v1/resource",method:"put",data:e})}function l(e){return Object(o["b"])({url:"/admin/sys/resource/image/api/delete?id="+e,method:"delete"})}function m(e){return Object(o["b"])({url:"/admin/v1/resource/upload",method:"post",data:e})}function f(e){return Object(o["b"])({url:"/admin/sys/resource/image/api/auditLog?businessId="+e,method:"get"})}function p(){return Object(o["b"])({url:"/admin/sys/resource/image/api/syncIndex",method:"post"})}function b(e){return Object(o["b"])({url:"/admin/sys/resource/image/api/diagram?processInstanceId="+e,method:"get"})}function h(e){return Object(o["b"])({url:"/admin/sys/resource/image/api/download?id="+e,method:"get",responseType:"blob"})}},c13d:function(e,t,r){"use strict";r.r(t);var o=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("a-drawer",{attrs:{width:"35%","label-col":4,"wrapper-col":14,visible:e.open},on:{close:e.onClose}},[r("a-divider",{attrs:{orientation:"left"}},[r("b",[e._v(e._s(e.formTitle))])]),r("a-form-model",{ref:"form",attrs:{model:e.form,rules:e.rules}},[r("a-form-model-item",{attrs:{label:"标题",prop:"title"}},[r("a-input",{attrs:{placeholder:"请输入标题"},model:{value:e.form.title,callback:function(t){e.$set(e.form,"title",t)},expression:"form.title"}})],1),r("a-form-model-item",{attrs:{label:"资源"}},[r("a-input",{staticStyle:{display:"none"},attrs:{placeholder:"请上传资源"},model:{value:e.form.url,callback:function(t){e.$set(e.form,"url",t)},expression:"form.url"}}),r("a-upload",{attrs:{name:"file",accept:".gif,.GIF,.jpg,.JPG,.jpeg,.JPEG,.png,.PNG,.bmp,.BMP,.webp,.WEBP","before-upload":e.beforeUpload},on:{change:e.uploadFile}},[r("a-button",{attrs:{disabled:e.disabled}},[e._v(" 上传图片 ")])],1),r("img",{directives:[{name:"show",rawName:"v-show",value:e.display,expression:"display"}],attrs:{src:e.form.url,width:"100",height:"100"}})],1),r("a-form-model-item",{attrs:{label:"备注",prop:"remark"}},[r("a-input",{attrs:{placeholder:"请输入备注",type:"textarea","allow-clear":""},model:{value:e.form.remark,callback:function(t){e.$set(e.form,"remark",t)},expression:"form.remark"}})],1),r("div",{staticClass:"bottom-control"},[r("a-space",[r("a-button",{attrs:{type:"primary",loading:e.submitLoading},on:{click:e.submitForm}},[e._v(" 保存 ")]),r("a-button",{attrs:{type:"dashed"},on:{click:e.cancel}},[e._v(" 取消 ")])],1)],1)],1)],1)},n=[],i=(r("d3b7"),r("765c")),a=(r("5880"),{name:"CreateForm",components:{},data:function(){return{submitLoading:!1,formTitle:"",tagInputVisible:!1,tagInputValue:"",form:{resourceId:void 0,title:void 0,url:void 0,code:"image",remark:void 0,processInstanceId:void 0},disabled:!1,open:!1,display:!1,rules:{title:[{required:!0,message:"标题不能为空",trigger:"blur"}],remark:[{required:!0,message:"备注不能为空",trigger:"blur"}]}}},filters:{},created:function(){},computed:{},watch:{},methods:{onClose:function(){this.open=!1},cancel:function(){this.open=!1,this.reset()},reset:function(){this.disabled=!1,this.form={resourceId:void 0,title:void 0,url:void 0,code:"image",remark:void 0,processInstanceId:void 0}},uploadFile:function(e){var t=this;if(e.fileList.length>0){this.disabled=!0;var r=new FormData;r.append("file",e.file),Object(i["uploadImage"])(r).then((function(e){t.form.url=e.data.url,t.display=!0}))}else this.display=!1,this.disabled=!1,this.form.url=void 0},beforeUpload:function(){return!1},handleAdd:function(){this.reset(),this.open=!0,this.formTitle="图片新增"},handleUpdate:function(e,t){var r=this;this.reset();var o=e?e.id:t;Object(i["getImage"])(o).then((function(e){r.form.resourceId=e.data.id,r.form.url=e.data.url,r.form.title=e.data.title,r.form.code="image",r.display=!0,r.form.processInstanceId=e.data.processInstanceId,r.form.remark=e.data.remark,r.open=!0,r.formTitle="图片修改"}))},submitForm:function(){var e=this;this.$refs.form.validate((function(t){if(!t)return!1;e.submitLoading=!0,void 0!==e.form.resourceId?Object(i["updateImage"])(e.form).then((function(t){e.$message.success("修改成功",3),e.open=!1,e.$emit("ok")})).finally((function(){e.submitLoading=!1})):Object(i["a"])(e.form).then((function(t){e.$message.success("新增成功",3),e.open=!1,e.$emit("ok")})).finally((function(){e.submitLoading=!1}))}))}}}),s=a,u=r("2877"),d=Object(u["a"])(s,o,n,!1,null,null,null);t["default"]=d.exports}}]);