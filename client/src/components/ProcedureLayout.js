import React from 'react';

export const ProcedureLayout = props => {
    const name = props.hospital.name;
    const procedures = props.procedures;

    return (
        <div>
            <h2>{name}</h2>
            <ul>
                {procedures.map(p => {
                    return (
                        <li key={p.id}>
                            <span>{p.name}</span>
                            <span>${p.price}</span>
                        </li>
                    )
                })
                }
            </ul>
        </div>
    )
};
