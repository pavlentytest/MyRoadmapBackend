package ru.boringowl.myroadmap.infrastructure.jpa

import ru.boringowl.myroadmap.domain.BookInfo
import ru.boringowl.myroadmap.domain.BookPost
import java.util.*
import javax.persistence.*

@Entity
@Table(name="books_post_table")
class JpaBookPost(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_post_id")
    var bookPostId: UUID? = null,
    @Column(columnDefinition="TEXT", name = "book_description")
    var description: String = "",
    @OneToMany(fetch = FetchType.EAGER, cascade= [CascadeType.ALL])
    var books: List<JpaBookInfo> = arrayListOf(),
    @ManyToOne
    var route: JpaRoute? = null
) {
    constructor(bookPost: BookPost) : this(bookPost.bookPostId, bookPost.description, bookPost.books.map { JpaBookInfo(it) })
    fun toBookPost() = BookPost().also {
        it.bookPostId = bookPostId
        it.books = books.map { b -> b.toBook() }
        it.description = description
    }
}

@Entity
@Table(name="books_table")
class JpaBookInfo (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    var bookId: UUID? = null,
    @Column(name = "book_url")
    var url: String = "",
    @Column(name = "book_filename")
    var filename: String = "",
    @Column(name = "book_size")
    var size: Int = 0
) {

    constructor(book: BookInfo) : this(null, book.url, book.filename, book.size)
    fun toBook() = BookInfo().also {
        it.url = url
        it.filename = filename
        it.size = size
    }
}
