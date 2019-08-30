import React, {Component} from 'react';

export default class SearchBar extends Component {

    state = {
        value: ''
    };

    handleChange = e => {
        this.setState({
            value: e.target.value
        });

        if (e.key === 'Enter') {
            this.handleSubmit(e)
        }
    };

    handleSubmit = e => {
        e.preventDefault();
        this.props.search(this.state.value, 10)
    };

    render() {
        return (
            <div className="search_icon_input">
                <i className="fas fa-search"/>
                <input type="text" onChange={this.handleChange} onKeyDown={this.handleChange}
                       placeholder={this.props.placeholder} className="home-search"/>

                <button className="primary" onClick={this.handleSubmit}> Search</button>
            </div>
        )
    }
}