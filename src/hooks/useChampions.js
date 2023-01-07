import {useEffect, useState} from "react";

export default function UseChampions() {
    const [playable, setPlayable] = useState(false)
    const [reloadChamps, setReloadChamps] = useState(false);
    useEffect(() => {
        setReloadChamps(!reloadChamps)
    }, [playable])

    const resetChampions = () => {
        setReloadChamps(!reloadChamps)
    }

    return {
        playable, setPlayable, resetChampions, reloadChamps
    }
}


