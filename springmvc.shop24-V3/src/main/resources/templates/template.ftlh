 <#macro template>
 <!DOCTYPE html>
<html>
    <head>
        <title>Shop 24</title>
        <meta charset="windows-1252">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="/webjars/bootstrap/5.1.0/css/bootstrap.min.css" rel="stylesheet"/>
    </head>
    <body>
        <div class="container">
        	<div class ="row">
	        <nav class="navbar navbar-expand-sm">
   				<div class="navbar-brand">
   					<img src="/images/Books.png" alt="Books" width="40" height="40">
   				</div>
   				<ul class="navbar-nav">
     				<li class="nav-item"><a class="nav-link" href="/">Home</a></li>
     				<li class="nav-item"><a class="nav-link" href="/products">Search Products</a></li>
     				<li class="nav-item"><a class="nav-link" href="/shoppingCart">Shopping Cart</a></li>
     				<li class="nav-item"><a class="nav-link" href="/addBook">Add Book</a></li>
     				<li class="nav-item"><a class="nav-link" href="/addAuthor">Add Author</a></li>
   				</ul>
			</nav>
			</div>
            <div class="row">
                <@content/>
            </div>
            <div class="row">
	            <h4>&copy; Schmalkalden University of Applied Sciences 2023</h4>
            </div>
        </div>        
    </body>
</html>
</#macro>
<#macro content>This is content from base template</#macro>
<#macro showSuccess>
	<#if successMessage??>
    	<div class="text-success mb-3">
        	${successMessage}
    	</div>
	</#if>
</#macro>
<#macro showError>
	<#if errorMessage??>
    	<div class="text-danger mb-3">
        	${errorMessage}
    	</div>
	</#if>
</#macro>

