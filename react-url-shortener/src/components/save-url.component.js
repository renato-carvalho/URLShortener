import React, { Component } from "react";
import UrlService from "../services/url.service";

export default class SaveUrl extends Component {
  constructor(props) {
    super(props);
    this.saveTutorial = this.saveTutorial.bind(this);
    this.onChangeTitle = this.onChangeTitle.bind(this);
    this.newTutorial = this.newTutorial.bind(this);

    this.state = {
      originalUrl: "",
      submitted: false
    };
  }

  onChangeTitle(e) {
    this.setState({
      originalUrl: e.target.value
    });
  }

  saveTutorial() {
    var data = {
      originalUrl: this.state.originalUrl
    };

    UrlService.create(data)
      .then(response => {
        this.setState({
          
          originalUrl: response.data.originalUrl,
          submitted: true
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  
  newTutorial() {
    this.setState({
      
      originalUrl: "",

      submitted: false
    });
  }

  render() {
    return (
      <div className="submit-form">
        {this.state.submitted ? (
          <div>
            <h4>You submitted successfully!</h4>
          </div>
        ) : (
          <div>
            <div className="form-group">
              <label htmlFor="title">URL</label>
              <input
                type="text"
                className="form-control"
                id="originalUrl"
                required
                value={this.state.originalUrl}
                onChange={this.onChangeTitle}
                name="originalUrl"
              />
            </div>

            <button onClick={this.saveTutorial} className="btn btn-success">
              Submit
            </button>
          </div>
        )}
      </div>
    );
  }
}
