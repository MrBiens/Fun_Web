$( function() {
    $("#productName" ).autocomplete({
      source: "admin/product/autocomplete",
          minLength: 3,
          select : function(event,ui){
            this.value=ui.item.label;
            $("#productId").val(ui.item.value);
            return false;
          }
    });
  } );