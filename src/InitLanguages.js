import i18next from 'i18next';
import translationEN from "./assets/locales/en/translation.json";
import translationDE from "./assets/locales/de/translation.json";
import {initReactI18next} from "react-i18next";

i18next
    .use(initReactI18next)
    .init({
        lng: 'en',
        debug: true,
        resources: {
            en: {
                translation: translationEN
            },
            de: {
                translation: translationDE
            }
        }
    })