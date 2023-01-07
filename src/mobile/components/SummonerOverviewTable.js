import * as React from 'react';
import {useState} from 'react';
import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";
import styled from "styled-components/macro";
import FilterMatchMode from "primereact/api";
import {GrCheckmark, GrClose} from "react-icons/gr";

export default function SummonerOverviewTable({statistics}) {
    const [filters] = useState({
        'summonerName': {value: null, matchMode: FilterMatchMode.STARTS_WITH},
        'championName': {value: null, matchMode: FilterMatchMode.STARTS_WITH}
    });
    const verifiedBodyTemplate = (rowData) => {
        return rowData.played ?
            <StyledPlayedSection>
                <p>{rowData.looses}L</p>
            </StyledPlayedSection>
            :
            <StyledPlayedSection>
                <StyledUncheck/>
            </StyledPlayedSection>;
    }
    return (
        <StyledTableContainer>
            <DataTable paginator rows={15} filters={filters} emptyMessage="No Summoners found" value={statistics} responsiveLayout="scroll" filterDisplay="row" showGridlines
                       globalFilterFields={['summonerName', 'championName']}>
                <Column filter filterPlaceholder="Search by name" field="summonerName" header="Username"/>
                <Column filter filterPlaceholder="Search by name" field="championName" header="Champion"/>
                <Column field="played" header="Played" dataType="boolean" body={verifiedBodyTemplate}/>
            </DataTable>
        </StyledTableContainer>
    )
}
const StyledPlayedSection = styled.section`
  display: flex;
`

const StyledTableContainer = styled.section`
  width: 80%;
  margin-top: 20px;
`
const StyledCheck = styled(GrCheckmark)`
  color: green;
`
const StyledUncheck = styled(GrClose)`
  color: red;
`