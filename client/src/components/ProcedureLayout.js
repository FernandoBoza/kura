import React from 'react';
import SearchBar from "./SearchBar";

export const ProcedureLayout = props => {
    const procedures = props.procedures;

    return (
        <div className={props.class}>
            <h2>Search Services</h2>
            <SearchBar/>
            <ul className="search_query">
                {procedures.map(p => {
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
};
