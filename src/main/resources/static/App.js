///**
// * Created by stark.zhang on 2016/3/28.
// */
//const React = require('react');
////const client = require('./client');
//
//class App extends React.Component {
//
//    constructor(props) {
//        super(props);
//        this.state = {items: []};
//    }
//
//
//    componentDidMount() {
//        $.ajax({
//            url: '/items',
//            dataType: 'json',
//            cache: false,
//            success: function (data) {
//                this.setState({items: data});
//            }.bind(this),
//            error: function (xhr, status, err) {
//                console.error(this.props.url, status, err.toString());
//            }.bind(this)
//        });
//
//        //client({method: 'GET', path: '/items'}).done(response => {
//        //    this.setState({items: response.entity._embedded.items});
//        //});
//    }
//
//    render() {
//        return (
//            <ItemList items={this.state.items}/>
//        )
//    }
//}
//
//class ItemList extends React.Component{
//    render() {
//        var items = this.props.items.map(item =>
//            <Item key={item._links.self.href} item={item}/>
//        );
//
//        return (
//            <table>
//                <tr>
//                    <th>id</th>
//                    <th>Description</th>
//                </tr>
//                {items}
//            </table>
//        )
//    }
//}
//
//class Item extends React.Component{
//    render() {
//        return (
//            <tr>
//                <td>{this.props.item.id}</td>
//                <td>{this.props.item.description}</td>
//            </tr>
//        )
//    }
//}
//
//React.render(
//    <App />,
//    document.getElementById('react')
//)

var converter = new Showdown.converter();

var CommentForm = React.createClass({displayName: "CommentForm",
    handleSubmit: function (e) {
        e.preventDefault();
        var author = this.refs.author.getDOMNode().value.trim();
        var text = this.refs.text.getDOMNode().value.trim();
        if (!author || !text) {
            return;
        }
        this.props.onCommentSubmit({author: author, text: text});
        this.refs.author.getDOMNode().value = '';
        this.refs.text.getDOMNode().value = '';
    },
    render: function () {
        return (
            React.createElement("form", {className: "commentForm", onSubmit: this.handleSubmit},
                React.createElement("input", {type: "text", placeholder: "Your name", ref: "author"}),
                React.createElement("input", {type: "text", placeholder: "Say something...", ref: "text"}),
                React.createElement("input", {type: "submit", value: "Post"})
            )
        );
    }
});

var Comment = React.createClass({displayName: "Comment",
    render: function () {
        var rawMarkup = converter.makeHtml(this.props.children.toString());
        return (
            React.createElement("div", {className: "comment"},
                React.createElement("h2", null, this.props.author),
                React.createElement("span", {dangerouslySetInnerHTML: {__html: rawMarkup}})
            )
        );
    }
});

var CommentList = React.createClass({displayName: "CommentList",
    render: function () {
        var commentNodes = this.props.data.map(function (comment, index) {
            return (
                React.createElement(Comment, {author: comment.description, key: index},
                    comment.text
                )
            );
        });
        return (
            React.createElement("div", {className: "commentList"},
                commentNodes
            )
        );
    }
});

var CommentBox = React.createClass({displayName: "items",
    handleCommentSubmit: function (item) {
        var items = this.state.data;
        items.push(item);
        this.setState({data: items}, function () {
            $.ajax({
                url: this.props.url,
                dataType: 'json',
                type: 'POST',
                data: items,
                success: function (data) {
                    this.setState({data: data});
                }.bind(this),
                error: function (xhr, status, err) {
                    console.error(this.props.url, status, err.toString());
                }.bind(this)
            });
        });
    },
    loadCommentsFromServer: function () {
        $.ajax({
            url: this.props.url,
            dataType: 'json',
            success: function (data) {
                this.setState({data: data});
            }.bind(this),
            error: function (xhr, status, err) {
                console.error(this.props.url, status, err.toString());
            }.bind(this)
        });
    },
    getInitialState: function () {
        return {data: this.props.data};
    },
    componentDidMount: function () {
        this.loadCommentsFromServer();
        setInterval(this.loadCommentsFromServer, this.props.pollInterval);
    },
    render: function () {
        return (
            React.createElement("div", {className: "commentBox"},
                React.createElement("h1", null, "Comments"),
                React.createElement(CommentList, {data: this.state.data}),
                React.createElement(CommentForm, {onCommentSubmit: this.handleCommentSubmit})
            )
        );
    }
});

var renderClient = function (comments) {
    var data = comments || [];
    React.render(
        React.createElement(CommentBox, {data: data, url: "/items", pollInterval: 5000}),
        document.getElementById("content")
    );
};

var renderServer = function (comments) {
    var data = Java.from(comments);
    return React.renderToString(
        React.createElement(CommentBox, {data: data, url: "comments.json", pollInterval: 5000})
    );
};