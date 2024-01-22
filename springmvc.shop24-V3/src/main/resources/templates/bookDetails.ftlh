<#import "/spring.ftl" as spring/>
<#include "template.ftlh">
<#macro content>
	<h3>Book Details</h3>
	<div class="col-12 col-sm-3">Title</div>
	<div class="col-12 col-sm-9 mb-3">${book.title}</div>
	<div class="col-12 col-sm-3">ISBN</div>
	<div class="col-12 col-sm-9 mb-3">${book.isbn13}</div>
	<div class="col-12 col-sm-3">Publisher</div>
	<div class="col-12 col-sm-9 mb-3">${book.publisher}</div>
	<div class="col-12 col-sm-3">Published at</div>
	<div class="col-12 col-sm-9 mb-3">${book.publishedDate}</div>
	<div class="col-12 col-sm-3">Number of pages</div>
	<div class="col-12 col-sm-9 mb-3">${book.numberOfPages}</div>
	<div class="col-12 col-sm-3">Language</div>
	<div class="col-12 col-sm-9 mb-3">${book.language}</div>
	<div class="col-12 col-sm-3">Authors</div>
	<div class="col-12 col-sm-9 mb-3"><#list authors as author>
          ${author.firstName} ${author.lastName}<#sep>,</#list></div>
	<form action="/backToProducts" method="POST">
	   	<button type="submit" class="btn btn-primary mb-3" name="action" value="back">Back to product list</button>
	</form>
</#macro>
<@template/>