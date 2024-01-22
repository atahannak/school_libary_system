<#import "/spring.ftl" as spring/>
<#include "template.ftlh">
<#macro content>
	<h3>Add Book</h3>
	<@showSuccess/>
	<@showError/>
	<form action="/addBook" method="POST">
        <div class="mb-3">
	        <label for="authorId">Author</label>
            <@spring.formSingleSelect "bookBean.authorId" authors "class='form-control'"/>
            <@spring.showErrors "<br>" "text-danger"/>
        </div>
    	<button type="submit" class="btn btn-primary" name="button" value="addAuthor">Add Author</button>
	    <table class="table table-striped table-hover table-responsive ">
	        <#list selectedAuthors>
        	<tr>
            	<th>Id</th>
            	<th>First name</th>
            	<th>Last name</th>
            	<th></th>
            </tr>
        	<#items as author>
        	<tr>
            	<td>${author.id}</td>
            	<td>${author.firstName}</td>
            	<td>${author.lastName}</td>
            	<td><button type="submit" class="btn btn-primary" name="button" value="${author.id}">Delete Author</button></td>
            </tr>
        	</#items>
        	</#list>
        </table>
   	<button type="submit" class="btn btn-primary" name="button" value="addBook">Add Book</button>
	</form>
</#macro>
<@template/>