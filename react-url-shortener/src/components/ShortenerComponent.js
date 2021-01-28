
import React from 'react';

const targetUrl = "http://localhost:8080";

class MyComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            originalUrl: "",
            shortUrl: "",
            labelUrl: "",
            originalUrlReverse: "",
            shortUrlReverse: "",
            labelUrlReverse: ""
        };
    }

    handleSubmit = async (event) => {
        event.preventDefault();
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ title: 'Simple' })
        };
        fetch(targetUrl + `/save?originalUrl=${this.state.originalUrl}`, requestOptions)
            .then(async response => {
                const data = await response.json();

                if (!response.ok) {
                    const error = (data && data.message) || response.status;
                    return Promise.reject(error);
                }

                this.setState({
                    shortUrl: targetUrl + "/" + data.id,
                    labelUrl: "Shortened URL: "
                });
            })
            .catch(error => this.setState({
                shortUrl: "",
                labelUrl: "Invalid URL"
            })
            )
    };

    handleSubmitReverse = async (event) => {
        event.preventDefault();
        var id = this.state.shortUrlReverse.substring(this.state.shortUrlReverse.lastIndexOf('/') + 1);
        fetch(targetUrl + `/getById?id=${id}`)
            .then(res => res.json())
            .then(
                (data) => {
                    this.setState({
                        originalUrlReverse: data.url,
                        labelUrlReverse: "Original URL: "
                    });
                }
            )
            .catch(error => this.setState({
                originalUrlReverse: "",
                labelUrlReverse: "Shortened URL not found"
            })
            )
    };



    render() {
        const { shortUrl, labelUrl, originalUrlReverse, labelUrlReverse } = this.state;

        return (
            <div>


                <form onSubmit={this.handleSubmit}>
                    <span className="formtext">URL Shortener</span>
                    <input
                        type="text"
                        value={this.state.originalUrl}
                        onChange={event => this.setState({ originalUrl: event.target.value })}
                        placeholder="Enter URL"
                        required
                    />
                    <button>Shorten</button>
                    <p class="result">{labelUrl} <a href={shortUrl}>{shortUrl}</a></p>
                </form>

                <form onSubmit={this.handleSubmitReverse} hidden>
                    <span className="formtext">Original URL</span>
                    <input
                        type="text"
                        value={this.state.shortUrlReverse}
                        onChange={event => this.setState({ shortUrlReverse: event.target.value })}
                        placeholder="Enter Shortened URL"
                        required
                    />
                    <button>Reverse</button>
                    <p class="result">{labelUrlReverse} <a href={originalUrlReverse}>{originalUrlReverse}</a></p>
                </form>
            </div>
        );

    }
}

export default MyComponent;