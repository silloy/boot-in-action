<!DOCTYPE html>
<html>
    <head>
        <title>Your Reading List</title>
        <link rel="stylesheet" href="/style.css"></link>
    </head>
    <body>
        <h2>Your Reading List</h2>
        <g:if test="${books}">
            <g:each in="${books}" var="book">
                <dl>
                    <dt class="bookHeadline">\
                        ${book.title} by ${book.author}
                        (ISBN: ${book.isbn})
                    </dt>
                    <dd class="bookDeacription">
                        <g:if test="book.description">
                            ${book.description}
                        </g:if>
                        <g:else>
                            No description available
                        </g:else>
                    </dd>
                </dl>
            </g:each>
        </g:if>
        <g:else>
            <p>Your have no books in your book list</p>
        </g:else>
        <hr/>
        <h3>Add a book</h3>
        <form method="POST">
            <label for="title">Title:</label>
                <input type="text" name="title" size="50" value="${book?.title}"></input><br/>
            <label for="author">Author:</label>
            <input type="text" name="author" size="50" value="${book?.author}"></input><br/>
            <label for="isbn">ISBN:</label>
            <input type="text" name="isbn" size="50" value="${book?.isbn}"></input><br/>
            <label for="description">Description:</label><br/>
            <textarea name="description" clos="80" rows="5">${book?.description}</textarea>
            %{-- CSRF 令牌 --}%
            <input type="hidden" name="{_csrf.parameterName}" value="${_csrf.token}" />
            <input type="submit" value="Add Book"></input>
        </form>
    </body>
</html>