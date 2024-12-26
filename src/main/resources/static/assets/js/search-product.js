<script>
    const apiBaseUrl = '/admin/product';

    function search_product() {
        let keySearch = document.getElementById("productName").value;
        if (keySearch.length >= 2) {
            $.ajax({
                url: apiBaseUrl + "/autocomplete?term=" + keySearch,
                method: 'GET',
                success: function(result) {
                    let productList = $("#productList");
                    productList.empty();  // Clear previous results
                    if (result.length > 0) {
                        productList.show();
                        result.forEach(item => {
                            productList.append(`<li class="dropdown-item" onclick="selectProduct('${item.label}', ${item.value})">${item.label}</li>`);
                        });
                    } else {
                        productList.hide();
                    }
                },
                error: function(error) {
                    console.log("Error:", error);
                }
            });
        } else {
            $("#productList").hide();
        }
    }

    function selectProduct(label, value) {
        $("#productName").val(label);
        $("#productId").val(value);
        $("#productList").hide();  // Hide the dropdown after selecting
    }
</script>