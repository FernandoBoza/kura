import React, {Component} from 'react';
import Fuse from "fuse.js";
import SearchBar from "./SearchBar";

export default class ProcedureLayout extends Component {

    state = {
        procedures: this.props.procedures
    };

    componentWillReceiveProps(nextProps) {
        // You don't have to do this check first, but it can help prevent an unneeded render
        if (nextProps.procedures !== this.state.procedures) {
            this.setState({procedures: nextProps.procedures});
        }
    }

    fuzzySearchProcedures = (value) => {
        var options = {
            shouldSort: true,
            threshold: 0.6,
            location: 0,
            distance: 50,
            maxPatternLength: 12,
            minMatchCharLength: 1,
            keys: [
                "name"
            ]
        };
        var fuse = new Fuse(this.props.procedures, options);
        if (value === '') {
            this.setState({
                procedures: this.props.procedures
            })
        } else {
            this.setState({
                procedures: fuse.search(value)
            })
        }
    };

    render() {
        return (
            <div className={this.props.class}>
                <h2>Search Services</h2>
                <SearchBar placeholder={'Search services'} search={this.fuzzySearchProcedures}/>
                <ul className="search_query">
                    {this.state.procedures.map(p => {
                        return (
                            <li key={p.id}>
                                <h3>{p.name}</h3>
                                <span>${p.price}</span>
                            </li>
                        )
                    })
                    }
                </ul>
            </div>
        )
    }
};
