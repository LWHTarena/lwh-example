/*ip输入框操作*/
$(".ip-input")
    .on("input",function(){
        /*限制非数字的输入，当长度为3时下一个输入框自动聚焦*/
        var val=$(this).val();
        val=val.replace(/\D/g,'')
        $(this).val(val);
        if(val.length==3&&$(this).next()){
            $(this).next().next().focus();
        }
    })
    .on("keydown",function(e){
        var self=$(this);
        var e=e||window.event;
        if(e.keyCode==110&&self.val()!=""&&self.next()){
            $(this).next().next().focus();
            return false
        }
        if(e.keyCode==8&&self.val().length==0&&self.prev()){
            $(this).prev().prev().focus();
        }
    })

/*存储网卡限制输入中文的"，"*/
$("[name='storageNet']").on("input",function(){
    var val=$(this).val();
    val=val.replace(/，/g,'')
    $(this).val(val);
});

function computedIp(el){
    var ip="";
    var dot=".";
    el.each(function(){
        ip+=$(this).val()+dot;
    })
    ip=ip.slice(0,-1);
    return ip
}

function check(){
    $(".physicalForm").each(function(i,v){
        /*检验非空输入框*/
        $(v).find(".text-input").each(function(index,item){
            var el=$(item);
            var val=$.trim(el.val());
            if(!val){
                el.siblings("label").addClass("show");
            }else{
                el.siblings("label").removeClass("show");
            }
        })

        /*输入时检验物内存大小*/
        var memory=$(v).find('[name="memory"]');
        if(parseInt(memory.val())<8){
            memory.siblings("label").addClass("show");
        }else{
            memory.siblings("label").removeClass("show");
        }


        /*检验物理机密码*/
        var phyPsw=$(v).find(".phy-psw-input");
        if(phyPsw.val().length>12||phyPsw.val().length<6){
            phyPsw.siblings("label").addClass("show");
        }else{
            phyPsw.siblings("label").removeClass("show");
        }
        /*检验物理机IP*/
        var phy_ip_el=$(v).find(".IP .ip-input");
        var phy_ip=computedIp(phy_ip_el);

        if(!testIP.test(phy_ip)){
            $(v).find(".IP").find(".error").addClass("show");
        }else{
            $(v).find(".IP").find(".error").removeClass("show");
        }

        /*检验物理机掩码*/
        var phy_ym_el=$(v).find(".YM .ip-input");
        var phy_ym=computedIp(phy_ym_el);

        if(!testIP.test(phy_ym)){
            $(v).find(".YM").find(".error").addClass("show");
        }else{
            $(v).find(".YM").find(".error").removeClass("show");
        }

        /*检验物理机网关*/
        var phy_wg_el=$(v).find(".WG .ip-input");
        var phy_wg=computedIp(phy_wg_el);

        if(!testIP.test(phy_wg)){
            $(v).find(".WG").find(".error").addClass("show");
        }else{
            $(v).find(".WG").find(".error").removeClass("show");
        }


        /*检验ipmi ip*/
        var impi_ip_el=$(v).find(".ip-config .ip-input");
        var impi_ip=computedIp(impi_ip_el);

        if(!testIP.test(impi_ip)){
            $(v).find(".ip-config .ipWrap").siblings("label").addClass("show");
        }else{
            $(v).find(".ip-config .ipWrap").siblings("label").removeClass("show");
        }

    })
}
