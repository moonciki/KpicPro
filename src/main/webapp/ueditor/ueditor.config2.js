/**
 *  ueditor����������
 *  �������������������༭��������
 */
/**************************��ʾ********************************
 * ���б�ע�͵��������ΪUEditorĬ��ֵ��
 * �޸�Ĭ������������ȷ���Ѿ���ȫ��ȷ�ò�������ʵ��;��
 * ��Ҫ�������޸ķ�����һ����ȡ���˴�ע�ͣ�Ȼ���޸ĳɶ�Ӧ��������һ������ʵ�����༭��ʱ�����Ӧ������
 * �������༭��ʱ����ֱ��ʹ�þɰ������ļ��滻�°������ļ�,���õ��ľɰ������ļ�����ȱ���¹�������Ĳ��������½ű�����
 **************************��ʾ********************************/


(function () {
    /**
     * �༭����Դ�ļ���·����������ʾ�ĺ����ǣ��Ա༭��ʵ����ҳ��Ϊ��ǰ·����ָ��༭����Դ�ļ�����dialog���ļ��У���·����
     * ���ںܶ�ͬѧ��ʹ�ñ༭����ʱ����ֵ�����·�����⣬�˴�ǿ�ҽ�����ʹ��"�������վ��Ŀ¼�����·��"�������á�
     * "�������վ��Ŀ¼�����·��"Ҳ������б�ܿ�ͷ������"/myProject/ueditor/"������·����
     * ���վ�����ж������ͬһ�㼶��ҳ����Ҫʵ�����༭������������ͬһUEditor��ʱ�򣬴˴���URL���ܲ�������ÿ��ҳ��ı༭����
     * ��ˣ�UEditor�ṩ����Բ�ͬҳ��ı༭���ɵ������õĸ�·����������˵������Ҫʵ�����༭����ҳ�����д�����´��뼴�ɡ���Ȼ����Ҫ��˴���URL���ڶ�Ӧ�����á�
     * window.UEDITOR_HOME_URL = "/xxxx/xxxx/";
     */
    var URL = window.UEDITOR_HOME_URL || "/ueditor/";

    /**
     * ���������塣ע�⣬�˴������漰��·�������ñ���©URL������
     */
    window.UEDITOR_CONFIG = {

        //Ϊ�༭��ʵ�����һ��·����������ܱ�ע��
        UEDITOR_HOME_URL : URL

        //ͼƬ�ϴ�������
        ,imageUrl:"http://up.qiniu.com/"            //ͼƬ�ϴ��ύ��ַ
        ,imagePath:"http://7xwibn.com1.z0.glb.clouddn.com/"                     //ͼƬ������ַ��������fixedImagePath,�����������󣬿���������
        //��ţ�����Ҫ�ĳ�file
        ,imageFieldName:"file"                   //ͼƬ���ݵ�key,���˴��޸ģ���Ҫ�ں�̨��Ӧ�ļ��޸Ķ�Ӧ����
        //,compressSide:0                            //�ȱ�ѹ���Ļ�׼��ȷ��maxImageSideLength�����Ĳ��ն���0Ϊ������ߣ�1Ϊ���տ�ȣ�2Ϊ���ո߶�
        //,maxImageSideLength:900                    //�ϴ�ͼƬ�������ı߳����������Զ��ȱ�����,�����ž�����һ���Ƚϴ��ֵ������������image.html��

        //ͿѻͼƬ������
        ,scrawlUrl:URL+"jsp/scrawlUp.jsp"           //Ϳѻ�ϴ���ַ
        ,scrawlPath:URL+"jsp/"                            //ͼƬ������ַ��ͬimagePath

        //�����ϴ�������
        ,fileUrl:URL+"jsp/fileUp.jsp"               //�����ϴ��ύ��ַ
        ,filePath:URL + "jsp/"                   //����������ַ��ͬimagePath

        //,fileFieldName:"file"                    //�����ύ�ı��������˴��޸ģ���Ҫ�ں�̨��Ӧ�ļ��޸Ķ�Ӧ����

        //Զ��ץȡ������
        //,catchRemoteImageEnable:true               //�Ƿ���Զ��ͼƬץȡ,Ĭ�Ͽ���
        ,catcherUrl:URL +"jsp/getRemoteImage.jsp"   //����Զ��ͼƬץȡ�ĵ�ַ
        ,catcherPath:URL + "jsp/"                  //ͼƬ������ַ��ͬimagePath
        //,catchFieldName:"upfile"                   //�ύ����̨Զ��ͼƬuri�ϼ������˴��޸ģ���Ҫ�ں�̨��Ӧ�ļ��޸Ķ�Ӧ����
        //,separater:'ue_separate_ue'               //�ύ����̨��Զ��ͼƬ��ַ�ַ����ָ���
        //,localDomain:[]                            //���ض���������������Զ��ͼƬץȡʱ������֮����������������µ�ͼƬ������ץȡ������,Ĭ�ϲ�ץȡ127.0.0.1��localhost

        //ͼƬ���߹���������
        ,imageManagerUrl: "imageManager.jsp"       //ͼƬ���߹���Ĵ����ַ
        ,imageManagerPath:"http://7xwibn.com1.z0.glb.clouddn.com/"                                    //ͼƬ������ַ��ͬimagePath

        //��Ļ��ͼ������
        ,snapscreenHost: location.hostname                                 //��Ļ��ͼ��server���ļ����ڵ���վ��ַ����ip���벻Ҫ��http://
        ,snapscreenServerUrl: URL +"jsp/imageUp.jsp" //��Ļ��ͼ��server�˱������UEditor�ķ�������Ϊ��URL +"server/upload/jsp/snapImgUp.jsp"��
        ,snapscreenPath: URL + "jsp/"
        ,snapscreenServerPort: location.port                                   //��Ļ��ͼ��server�˶˿�
        //,snapscreenImgAlign: ''                                //��ͼ��ͼƬĬ�ϵ��Ű淽ʽ

        //wordת��������
        ,wordImageUrl:URL + "jsp/imageUp.jsp"             //wordת���ύ��ַ
        ,wordImagePath:URL + "jsp/"                       //
        //,wordImageFieldName:"upfile"                     //wordת��������˴��޸ģ���Ҫ�ں�̨��Ӧ�ļ��޸Ķ�Ӧ����

        //��ȡ��Ƶ���ݵĵ�ַ
        ,getMovieUrl:URL+"jsp/getMovie.jsp"                   //��Ƶ���ݻ�ȡ��ַ

        //�������ϵ����еĹ��ܰ�ť�������򣬿�����new�༭����ʵ��ʱѡ���Լ���Ҫ�Ĵ��¶���
        , toolbars:[
            ['undo', 'redo', 'insertimage', 'emotion', 'map', 'date', 'time', 'spechars', 'bold', 'italic', 'underline', 'fontborder', 'backcolor', 'fontsize', 'fontfamily', 'justifyleft', 'justifyright', 'justifycenter', 'justifyjustify', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', 'link', 'unlink']
        ],
        //�������ڹ�������ʱ��ʾ��tooltip��ʾ,����֧���Զ����������ã�����������ֵΪ׼
//        ,labelMap:{
//            'anchor':'', 'undo':''
//        }
        //webAppKey
        //�ٶ�Ӧ�õ�APIkey��ÿ��վ����������ȥ�ٶȹ���ע��һ��key��������ʹ��app����
        //,webAppKey:""

        //����������,Ĭ����zh-cn������Ҫ�Ļ�Ҳ����ʹ�����������ķ�ʽ���Զ��������л�����Ȼ��ǰ��������lang�ļ����´��ڶ�Ӧ�������ļ���
        //langֵҲ����ͨ���Զ���ȡ (navigator.language||navigator.browserLanguage ||navigator.userLanguage).toLowerCase()
        //,lang:"zh-cn"
        //,langPath:URL +"lang/"

        //����������,Ĭ����default������Ҫ�Ļ�Ҳ����ʹ�����������ķ�ʽ���Զ��������л�����Ȼ��ǰ��������themes�ļ����´��ڶ�Ӧ�������ļ���
        //��������Ƥ��:default
        //,theme:'default'
        //,themePath:URL +"themes/"

        //��ʵ�����༭����ҳ���ֶ��޸ĵ�domain���˴���Ҫ����Ϊtrue
        //,customDomain:false

        //���getAllHtml���������ڶ�Ӧ��head��ǩ�����Ӹñ������á�
        //,charset:"utf-8"

        //����������Ŀ
        //,isShow : true    //Ĭ����ʾ�༭��

        //,initialContent:'��ӭʹ��ueditor!'    //��ʼ���༭��������,Ҳ����ͨ��textarea/script��ֵ������������

        //,initialFrameWidth:1000  //��ʼ���༭�����,Ĭ��1000
        //,initialFrameHeight:320  //��ʼ���༭���߶�,Ĭ��320

        //,autoClearinitialContent:true //�Ƿ��Զ�����༭����ʼ���ݣ�ע�⣺���focus��������Ϊtrue,���ҲΪ�棬��ô�༭��һ�����ͻᴥ�����³�ʼ�������ݿ�������

        //,iframeCssUrl: URL + '/themes/iframe.css' //���༭���ڲ�����һ��css�ļ�

        //,textarea:'editorValue' // �ύ��ʱ����������ȡ�༭���ύ���ݵ����õĲ�������ʵ��ʱ���Ը�����name���ԣ��Ὣname������ֵ��Ϊÿ��ʵ���ļ�ֵ������ÿ��ʵ������ʱ���������ֵ

        //,focus:false //��ʼ��ʱ���Ƿ��ñ༭����ý���true��false

        //,autoClearEmptyNode : true //getContentʱ���Ƿ�ɾ���յ�inlineElement�ڵ㣨����Ƕ�׵������

        //,fullscreen : false //�Ƿ�����ʼ��ʱ��ȫ����Ĭ�Ϲر�

        //,readonly : false //�༭����ʼ��������,�༭�����Ƿ���ֻ���ģ�Ĭ����false

        //,zIndex : 900     //�༭���㼶�Ļ���,Ĭ����900

        //,imagePopup:true      //ͼƬ�����ĸ��㿪�أ�Ĭ�ϴ�

        //����Զ��壬��ø�p��ǩ���µ��иߣ�Ҫ����������ʱ������������
        initialStyle:'p{font-size:12px;color:#5B5B5B;line-height:1.5em}',//�༭���㼶�Ļ���,���������ı������

        //,autoSyncData:true //�Զ�ͬ���༭��Ҫ�ύ������
        //,emotionLocalization:false //�Ƿ������鱾�ػ���Ĭ�Ϲرա���Ҫ������ȷ��emotion�ļ����°��������ṩ��images�����ļ���

        pasteplain:true,  //�Ƿ�Ĭ��Ϊ���ı�ճ����falseΪ��ʹ�ô��ı�ճ����trueΪʹ�ô��ı�ճ��
        //���ı�ճ��ģʽ�µĹ��˹���
        'filterTxtRules' : function(){
            function transP(node){
                node.tagName = 'p';
                node.setStyle();
            }
            return {
                //ֱ��ɾ�������ֽڵ�����
                '-' : 'script style object iframe embed input select',
                'p': {$:{}},
                'div':{'$':{}},
                'li':{'$':{}},
                'caption':transP,
                'th':transP,
                'tr':transP,
                'h1':transP,'h2':transP,'h3':transP,'h4':transP,'h5':transP,'h6':transP,
                'td':function(node){
                    //û�����ݵ�tdֱ��ɾ��
                    var txt = !!node.innerText();
                    if(txt){
                        node.parentNode.insertAfter(UE.uNode.createText(' &nbsp; &nbsp;'),node);
                    }
                    node.parentNode.removeChild(node,node.innerText())
                }
            }
        }(),
        //,allHtmlEnabled:false //�ύ����̨�������Ƿ��������html�ַ���
        //iframeUrlMap
        //dialog���ݵ�·�� ���ᱻ�滻��URL,������һ���򿪣����������е�dialog��Ĭ��·��
        //,iframeUrlMap:{
        // 'anchor':'~/dialogs/anchor/anchor.html',
        // }

        //insertorderedlist
        //�����б����������,ֵ����ʱ֧�ֶ������Զ�ʶ��������ֵ�����Դ�ֵΪ׼
//        ,'insertorderedlist':{
//              //�Զ�����ʽ
//                'num':'1,2,3...',
//                'num1':'1),2),3)...',
//                'num2':'(1),(2),(3)...',
//                'cn':'һ,��,��....',
//                'cn1':'һ),��),��)....',
//                'cn2':'(һ),(��),(��)....',
//             //ϵͳ�Դ�
//             'decimal' : '' ,         //'1,2,3...'
//             'lower-alpha' : '' ,    // 'a,b,c...'
//             'lower-roman' : '' ,    //'i,ii,iii...'
//             'upper-alpha' : '' , lang   //'A,B,C'
//             'upper-roman' : ''      //'I,II,III...'
//        }

        //insertunorderedlist
        //�����б���������ã�ֵ����ʱ֧�ֶ������Զ�ʶ��������ֵ�����Դ�ֵΪ׼
        //,insertunorderedlist : {
        //              //�Զ�����ʽ
//        'dash' :'�� ���ۺ�',
//        'dot':' �� СԲȦ'
//             //ϵͳ�Դ�
        //    'circle' : '',  // '�� СԲȦ'
        //    'disc' : '',    // '�� СԲ��'
        //    'square' : ''   //'�� С����'
        //}
//        ,listDefaultPaddingLeft : '30'//Ĭ�ϵ���������Ļ�����
//        ,listiconpath : 'http://bs.baidu.com/listicon/'//�Զ����ŵ�·��
//        ,maxListLevel : 3 //���ƿ���tab�ļ���-1������
        //fontfamily
        //�������� label����֧�ֶ������Զ��л��������ã���������ֵΪ׼
//        ,'fontfamily':[
//            { label:'',name:'songti',val:'����,SimSun'},
//            { label:'',name:'kaiti',val:'����,����_GB2312, SimKai'},
//            { label:'',name:'yahei',val:'΢���ź�,Microsoft YaHei'},
//            { label:'',name:'heiti',val:'����, SimHei'},
//            { label:'',name:'lishu',val:'����, SimLi'},
//            { label:'',name:'andaleMono',val:'andale mono'},
//            { label:'',name:'arial',val:'arial, helvetica,sans-serif'},
//            { label:'',name:'arialBlack',val:'arial black,avant garde'},
//            { label:'',name:'comicSansMs',val:'comic sans ms'},
//            { label:'',name:'impact',val:'impact,chicago'},
//            { label:'',name:'timesNewRoman',val:'times new roman'}
//          ]

        //fontsize
        //�ֺ�
        //,'fontsize':[10, 11, 12, 14, 16, 18, 20, 24, 36]

        //paragraph
        //�����ʽ ֵ����ʱ֧�ֶ������Զ�ʶ�������ã���������ֵΪ׼
        //,'paragraph':{'p':'', 'h1':'', 'h2':'', 'h3':'', 'h4':'', 'h5':'', 'h6':''}

        //rowspacingtop
        //�μ�� ֵ����ʾ��������ͬ
        //,'rowspacingtop':['5', '10', '15', '20', '25']

        //rowspacingBottom
        //�μ�� ֵ����ʾ��������ͬ
        //,'rowspacingbottom':['5', '10', '15', '20', '25']

        //lineheight
        //���ڼ�� ֵ����ʾ��������ͬ
        //,'lineheight':['1', '1.5','1.75','2', '3', '4', '5']

        //customstyle
        //�Զ�����ʽ����֧�ֹ��ʻ����˴�����ֵ���������ʾֵ
        //block��Ԫ�����������ö�����߼����õģ�inline��Ԫ������BIU���߼�����
        //����ʹ��һЩ���õı�ǩ
        //����˵��
        //tag ʹ�õı�ǩ����
        //label ��ʾ������Ҳ��������ʶ��ͬ���͵ı�ʶ����ע�����ֵÿ��Ҫ��ͬ��
        //style ��ӵ���ʽ
        //ÿһ���������һ���Զ������ʽ
        //,'customstyle':[
        //      {tag:'h1', name:'tc', label:'', style:'border-bottom:#ccc 2px solid;padding:0 4px 0 0;text-align:center;margin:0 0 20px 0;'},
        //      {tag:'h1', name:'tl',label:'', style:'border-bottom:#ccc 2px solid;padding:0 4px 0 0;margin:0 0 10px 0;'},
        //      {tag:'span',name:'im', label:'', style:'font-style:italic;font-weight:bold'},
        //      {tag:'span',name:'hi', label:'', style:'font-style:italic;font-weight:bold;color:rgb(51, 153, 204)'}
        //  ]

        //�Ҽ��˵������ݣ����Բο�plugins/contextmenu.js��ߵ�Ĭ�ϲ˵������ӣ�label����֧�ֹ��ʻ��������Դ�����Ϊ׼
//        ,contextMenu:[
//            {
//                label:'',       //��ʾ������
//                cmdName:'selectall',//ִ�е�command������������Ҽ��˵�ʱ
//                //exec��ѡ������exec�ͻ��ڵ��ʱִ�����function�����ȼ�����cmdName
//                exec:function () {
//                    //this�ǵ�ǰ�༭����ʵ��
//                    //this.ui._dialogs['inserttableDialog'].open();
//                }
//            }
//           ]

        //��ݲ˵�
        //,shortcutMenu:["fontfamily", "fontsize", "bold", "italic", "underline", "forecolor", "backcolor", "insertorderedlist", "insertunorderedlist"]

        //
        //wordCount
        //,wordCount:true          //�Ƿ�������ͳ��
        //,maximumWords:10000       //���������ַ���
        //����ͳ����ʾ��{#count}����ǰ������{#leave}����������������ַ���,����֧�ֶ������Զ��л������򰴴�������ʾ
        //,wordCountMsg:''   //��ǰ������ {#count} ���ַ���������������{#leave} ���ַ�
        //��������������ʾ  ����֧�ֶ������Զ��л������򰴴�������ʾ
        //,wordOverFlowMsg:''    //<span style="color:red;">��������ַ������Ѿ������������ֵ�����������ܻ�ܾ����棡</span>

        //highlightcode
        // �������ʱ��Ҫ���صĵ����������·��
        // ,highlightJsUrl:URL + "third-party/SyntaxHighlighter/shCore.js"
        // ,highlightCssUrl:URL + "third-party/SyntaxHighlighter/shCoreDefault.css"

        //tab
        //���tab��ʱ�ƶ��ľ���,tabSize������tabNodeʲô�ַ���Ϊ��λ
        //,tabSize:4
        //,tabNode:'&nbsp;'

        //elementPathEnabled
        //�Ƿ�����Ԫ��·����Ĭ������ʾ
        elementPathEnabled : false

        //removeFormat
        //�����ʽʱ����ɾ���ı�ǩ������
        //removeForamtTags��ǩ
        //,removeFormatTags:'b,big,code,del,dfn,em,font,i,ins,kbd,q,samp,small,span,strike,strong,sub,sup,tt,u,var'
        //removeFormatAttributes����
        //,removeFormatAttributes:'class,style,lang,width,height,align,hspace,valign'

        //undo
        //���������˵Ĵ���,Ĭ��20
        //,maxUndoCount:20
        //��������ַ���������ֵʱ������һ���ֳ�
        //,maxInputCount:1

        //autoHeightEnabled
        // �Ƿ��Զ�����,Ĭ��true
        //,autoHeightEnabled:true

        //scaleEnabled
        //�Ƿ�������쳤��,Ĭ��true(������ʱ���Զ�����ʧЧ)
        //,scaleEnabled:false
        //,minFrameWidth:800    //�༭���϶�ʱ��С���,Ĭ��800
        //,minFrameHeight:220  //�༭���϶�ʱ��С�߶�,Ĭ��220

        //autoFloatEnabled
        //�Ƿ񱣳�toolbar��λ�ò���,Ĭ��true
        //,autoFloatEnabled:true
        //����ʱ��������������������ĸ߶ȣ�����ĳЩ���й̶�ͷ����ҳ��
        //,topOffset:30
        //�༭���ײ����빤�����߶�(����������ڵ��ڱ༭���߶ȣ���������Ч)
        //,toolbarTopOffset:400

        //indentValue
        //������������,Ĭ����2em
        //,indentValue:'2em'

        //pageBreakTag
        //��ҳ��ʶ��,Ĭ����_ueditor_page_break_tag_
        //,pageBreakTag:'_ueditor_page_break_tag_'

        //sourceEditor
        //Դ��Ĳ鿴��ʽ,codemirror �Ǵ��������textarea���ı���,Ĭ����codemirror
        //ע��Ĭ��codemirrorֻ����ie8+�ͷ�ie��ʹ��
        //,sourceEditor:"codemirror"
        //���sourceEditor��codemirror����������һ����������
        //codeMirrorJsUrl js���ص�·����Ĭ���� URL + "third-party/codemirror/codemirror.js"
        //,codeMirrorJsUrl:URL + "third-party/codemirror/codemirror.js"
        //codeMirrorCssUrl css���ص�·����Ĭ���� URL + "third-party/codemirror/codemirror.css"
        //,codeMirrorCssUrl:URL + "third-party/codemirror/codemirror.css"
        //�༭����ʼ����ɺ��Ƿ����Դ��ģʽ��Ĭ��Ϊ��
        //,sourceEditorFirst:false

        //autotypeset
        //  //�Զ��Ű����
        //  ,autotypeset:{
        //      mergeEmptyline : true,         //�ϲ�����
        //      removeClass : true,           //ȥ�������class
        //      removeEmptyline : false,      //ȥ������
        //      textAlign : "left" ,           //������Ű淽ʽ�������� left,right,center,justify ȥ��������Ա�ʾ��ִ���Ű�
        //      imageBlockLine : 'center',      //ͼƬ�ĸ�����ʽ����ռһ�о���,���Ҹ�����Ĭ��: center,left,right,none ȥ��������Ա�ʾ��ִ���Ű�
        //      pasteFilter : false,            //���ݹ������û��ճ������������
        //      clearFontSize : false,          //ȥ�����е���Ƕ�ֺţ�ʹ�ñ༭��Ĭ�ϵ��ֺ�
        //      clearFontFamily : false,        //ȥ�����е���Ƕ���壬ʹ�ñ༭��Ĭ�ϵ�����
        //      removeEmptyNode : false ,       // ȥ���սڵ�
        //      //����ȥ���ı�ǩ
        //      removeTagNames : {��ǩ����:1},
        //      indent : false,                 // ��������
        //      indentValue : '2em'             //���������Ĵ�С
        //  },
        //��д���˹���
        //filterRules : {}
    };
})();
