/*!
 AutoFill 2.1.2
 ©2008-2015 SpryMedia Ltd - datatables.net/license
*/
(function(e){"function"===typeof define&&define.amd?define(["jquery","datatables.net"],function(j){return e(j,window,document)}):"object"===typeof exports?module.exports=function(j,i){j||(j=window);if(!i||!i.fn.dataTable)i=require("datatables.net")(j,i).$;return e(i,j,j.document)}:e(jQuery,window,document)})(function(e,j,i,q){var l=e.fn.dataTable,o=0,m=function(b,c){if(!l.versionCheck||!l.versionCheck("1.10.8"))throw"Warning: AutoFill requires DataTables 1.10.8 or greater";this.c=e.extend(!0,{},l.defaults.autoFill,
m.defaults,c);this.s={dt:new l.Api(b),namespace:".autoFill"+o++,scroll:{},scrollInterval:null,handle:{height:0,width:0}};this.dom={handle:e('<div class="dt-autofill-handle"/>'),select:{top:e('<div class="dt-autofill-select top"/>'),right:e('<div class="dt-autofill-select right"/>'),bottom:e('<div class="dt-autofill-select bottom"/>'),left:e('<div class="dt-autofill-select left"/>')},background:e('<div class="dt-autofill-background"/>'),list:e('<div class="dt-autofill-list">'+this.s.dt.i18n("autoFill.info",
"")+"<ul/></div>"),dtScroll:null,offsetParent:null};this._constructor()};e.extend(m.prototype,{_constructor:function(){var b=this,c=this.s.dt,a=e("div.dataTables_scrollBody",this.s.dt.table().container());a.length&&(this.dom.dtScroll=a,"static"===a.css("position")&&a.css("position","relative"));this._focusListener();this.dom.handle.on("mousedown",function(a){b._mousedown(a);return false});c.on("destroy.autoFill",function(){c.off(".autoFill");e(c.table().body()).off(b.s.namespace);e(i.body).off(b.s.namespace)})},
_attach:function(b){var c=this.s.dt,a=c.cell(b).index(),d=this.dom.handle,h=this.s.handle,f=e("div.dataTables_scrollBody",this.s.dt.table().container()),g=0,k=0;if(!a||-1===c.columns(this.c.columns).indexes().indexOf(a.column))this._detach();else{this.dom.offsetParent||(this.dom.offsetParent=e(b).offsetParent());if(!h.height||!h.width)d.appendTo("body"),h.height=d.outerHeight(),h.width=d.outerWidth();a=e(b).position();f.length&&this.dom.offsetParent[0]!==c.table().node()&&(k=f.scrollTop(),g=f.scrollLeft());
this.dom.attachedTo=b;d.css({top:a.top+b.offsetHeight-h.height+k,left:a.left+b.offsetWidth-h.width+g}).appendTo(this.dom.offsetParent)}},_actionSelector:function(b){var c=this,a=this.s.dt,d=m.actions,h=[];e.each(d,function(c,d){d.available(a,b)&&h.push(c)});if(1===h.length&&!1===this.c.alwaysAsk){var f=d[h[0]].execute(a,b);this._update(f,b)}else{var g=this.dom.list.children("ul").empty();h.push("cancel");e.each(h,function(h,f){g.append(e("<li/>").append('<div class="dt-autofill-question">'+d[f].option(a,
b)+"<div>").append(e('<div class="dt-autofill-button">').append(e('<button class="'+m.classes.btn+'">'+a.i18n("autoFill.button","&gt;")+"</button>").on("click",function(){var h=d[f].execute(a,b,e(this).closest("li"));c._update(h,b);c.dom.background.remove();c.dom.list.remove()}))))});this.dom.background.appendTo("body");this.dom.list.appendTo("body");this.dom.list.css("margin-top",-1*(this.dom.list.outerHeight()/2))}},_detach:function(){this.dom.attachedTo=null;this.dom.handle.detach()},_drawSelection:function(b){var c=
this.s.dt,a=this.s.start,d=e(this.dom.start),h=e(b),f={row:c.rows({page:"current"}).nodes().indexOf(h.parent()[0]),column:h.index()};if(c.cell(h).any()&&-1!==c.columns(this.c.columns).indexes().indexOf(f.column)){this.s.end=f;var g,k,b=a.row<f.row?d:h;g=a.row<f.row?h:d;k=a.column<f.column?d:h;d=a.column<f.column?h:d;b=b.position().top;k=k.position().left;a=g.position().top+g.outerHeight()-b;d=d.position().left+d.outerWidth()-k;if((g=this.dom.dtScroll)&&this.dom.offsetParent[0]!==c.table().node())b+=
g.scrollTop(),k+=g.scrollLeft();c=this.dom.select;c.top.css({top:b,left:k,width:d});c.left.css({top:b,left:k,height:a});c.bottom.css({top:b+a,left:k,width:d});c.right.css({top:b,left:k+d,height:a})}},_editor:function(b){var c=this.s.dt,a=this.c.editor;if(a){for(var d={},h=[],e=a.fields(),g=0,k=b.length;g<k;g++)for(var i=0,m=b[g].length;i<m;i++){var n=b[g][i],j=c.settings()[0].aoColumns[n.index.column],l=j.editField;if(l===q)for(var j=j.mData,p=0,o=e.length;p<o;p++){var r=a.field(e[p]);if(r.dataSrc()===
j){l=r.name();break}}if(!l)throw"Could not automatically determine field data. Please see https://datatables.net/tn/11";d[l]||(d[l]={});j=c.row(n.index.row).id();d[l][j]=n.set;h.push(n.index)}a.bubble(h,!1).multiSet(d).submit()}},_emitEvent:function(b,c){this.s.dt.iterator("table",function(a){e(a.nTable).triggerHandler(b+".dt",c)})},_focusListener:function(){var b=this,c=this.s.dt,a=this.s.namespace,d=null!==this.c.focus?this.c.focus:c.settings()[0].keytable?"focus":"hover";if("focus"===d)c.on("key-focus.autoFill",
function(a,c,d){b._attach(d.node())}).on("key-blur.autoFill",function(){b._detach()});else if("click"===d)e(c.table().body()).on("click"+a,"td, th",function(){b._attach(this)}),e(i.body).on("click"+a,function(a){e(a.target).parents().filter(c.table().body()).length||b._detach()});else e(c.table().body()).on("mouseenter"+a,"td, th",function(){b._attach(this)}).on("mouseleave"+a,function(a){e(a.relatedTarget).hasClass("dt-autofill-handle")||b._detach()})},_mousedown:function(b){var c=this,a=this.s.dt;
this.dom.start=this.dom.attachedTo;this.s.start={row:a.rows({page:"current"}).nodes().indexOf(e(this.dom.start).parent()[0]),column:e(this.dom.start).index()};e(i.body).on("mousemove.autoFill",function(a){c._mousemove(a)}).on("mouseup.autoFill",function(a){c._mouseup(a)});var a=this.dom.select,d=e(this.s.dt.table().body()).offsetParent();a.top.appendTo(d);a.left.appendTo(d);a.right.appendTo(d);a.bottom.appendTo(d);this._drawSelection(this.dom.start,b);this.dom.handle.css("display","none");b=this.dom.dtScroll;
this.s.scroll={windowHeight:e(j).height(),windowWidth:e(j).width(),dtTop:b?b.offset().top:null,dtLeft:b?b.offset().left:null,dtHeight:b?b.outerHeight():null,dtWidth:b?b.outerWidth():null}},_mousemove:function(b){var c=b.target.nodeName.toLowerCase();"td"!==c&&"th"!==c||(this._drawSelection(b.target,b),this._shiftScroll(b))},_mouseup:function(){e(i.body).off(".autoFill");var b=this.s.dt,c=this.dom.select;c.top.remove();c.left.remove();c.right.remove();c.bottom.remove();this.dom.handle.css("display",
"block");var c=this.s.start,a=this.s.end;if(!(c.row===a.row&&c.column===a.column)){for(var d=this._range(c.row,a.row),c=this._range(c.column,a.column),a=[],h=b.settings()[0],f=h.aoColumns,g=0;g<d.length;g++)a.push(e.map(c,function(a){var a=b.cell(":eq("+d[g]+")",a+":visible",{page:"current"}),c=a.data(),e=a.index(),i=f[e.column].editField;i!==q&&(c=h.oApi._fnGetObjectDataFn(i)(b.row(e.row).data()));return{cell:a,data:c,label:a.data(),index:e}}));this._actionSelector(a);clearInterval(this.s.scrollInterval);
this.s.scrollInterval=null}},_range:function(b,c){var a=[],d;if(b<=c)for(d=b;d<=c;d++)a.push(d);else for(d=b;d>=c;d--)a.push(d);return a},_shiftScroll:function(b){var c=this,a=this.s.scroll,d=!1,e=b.pageY-i.body.scrollTop,f=b.pageX-i.body.scrollLeft,g,k,j,l;65>e?g=-5:e>a.windowHeight-65&&(g=5);65>f?k=-5:f>a.windowWidth-65&&(k=5);null!==a.dtTop&&b.pageY<a.dtTop+65?j=-5:null!==a.dtTop&&b.pageY>a.dtTop+a.dtHeight-65&&(j=5);null!==a.dtLeft&&b.pageX<a.dtLeft+65?l=-5:null!==a.dtLeft&&b.pageX>a.dtLeft+a.dtWidth-
65&&(l=5);g||k||j||l?(a.windowVert=g,a.windowHoriz=k,a.dtVert=j,a.dtHoriz=l,d=!0):this.s.scrollInterval&&(clearInterval(this.s.scrollInterval),this.s.scrollInterval=null);!this.s.scrollInterval&&d&&(this.s.scrollInterval=setInterval(function(){if(a.windowVert)i.body.scrollTop=i.body.scrollTop+a.windowVert;if(a.windowHoriz)i.body.scrollLeft=i.body.scrollLeft+a.windowHoriz;if(a.dtVert||a.dtHoriz){var b=c.dom.dtScroll[0];if(a.dtVert)b.scrollTop=b.scrollTop+a.dtVert;if(a.dtHoriz)b.scrollLeft=b.scrollLeft+
a.dtHoriz}},20))},_update:function(b,c){if(!1!==b){var a=this.s.dt,d;this._emitEvent("preAutoFill",[a,c]);this._editor(c);if(null!==this.c.update?this.c.update:!this.c.editor){for(var e=0,f=c.length;e<f;e++)for(var g=0,i=c[e].length;g<i;g++)d=c[e][g],d.cell.data(d.set);a.draw(!1)}this._emitEvent("autoFill",[a,c])}}});m.actions={increment:{available:function(b,c){return e.isNumeric(c[0][0].label)},option:function(b){return b.i18n("autoFill.increment",'Increment / decrement each cell by: <input type="number" value="1">')},
execute:function(b,c,a){for(var b=1*c[0][0].data,a=1*e("input",a).val(),d=0,h=c.length;d<h;d++)for(var f=0,g=c[d].length;f<g;f++)c[d][f].set=b,b+=a}},fill:{available:function(){return!0},option:function(b,c){return b.i18n("autoFill.fill","Fill all cells with <i>"+c[0][0].label+"</i>")},execute:function(b,c){for(var a=c[0][0].data,d=0,e=c.length;d<e;d++)for(var f=0,g=c[d].length;f<g;f++)c[d][f].set=a}},fillHorizontal:{available:function(b,c){return 1<c.length&&1<c[0].length},option:function(b){return b.i18n("autoFill.fillHorizontal",
"Fill cells horizontally")},execute:function(b,c){for(var a=0,d=c.length;a<d;a++)for(var e=0,f=c[a].length;e<f;e++)c[a][e].set=c[a][0].data}},fillVertical:{available:function(b,c){return 1<c.length&&1<c[0].length},option:function(b){return b.i18n("autoFill.fillVertical","Fill cells vertically")},execute:function(b,c){for(var a=0,d=c.length;a<d;a++)for(var e=0,f=c[a].length;e<f;e++)c[a][e].set=c[0][e].data}},cancel:{available:function(){return!1},option:function(b){return b.i18n("autoFill.cancel",
"Cancel")},execute:function(){return!1}}};m.version="2.1.2";m.defaults={alwaysAsk:!1,focus:null,columns:"",update:null,editor:null};m.classes={btn:"btn"};e(i).on("preInit.dt.autofill",function(b,c){if("dt"===b.namespace){var a=c.oInit.autoFill,d=l.defaults.autoFill;if(a||d)d=e.extend({},a,d),!1!==a&&new m(c,d)}});l.AutoFill=m;return l.AutoFill=m});
