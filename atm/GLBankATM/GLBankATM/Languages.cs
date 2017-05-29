using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GLBankATM
{
    class Languages
    {
        private static string[] enterPinText = {"Enter PIN", "Zadajte PIN"};
        private static string[] cardBlockedText = { "CARD BLOCKED", "KARTA ZABLOKOVANA" };
        private static string[] menuWithDrawText = { "Withdraw", "Vyber hotovosti" };
        private static string[] menuBalanceText = { "Balance", "Zostatok" };
        private static string[] menuPinText = { "Change PIN", "Zmena PIN" };
        private static string[] wrongPinText = { "PIN WRONG", "NESPRAVNY PIN" };
        private static string[] changePinText = { "Enter new PIN:", "Zadaj novy PIN:" };
        private static string[] otherAmountText = { "Other amount", "Ina ciastka" };
        private static string[] enterValueText = { "Enter value", "Zadaj hodnotu" };
        private static string[] pinChangedText = { "PIN Changed!", "PIN Zmeneny!" };
        private static string[] takeMoneyText = { "Take your money", "Vezmite si peniaze" };
        private static string[] notEnoughMoneyText = { "Not enough balance", "Nedostatok penazi" };
        private static string[] badAmountText = { "Bad amount", "Zla suma" };

        public static string getEnterPinText(Language language)
        {
            if (language.Equals(Language.ENG))
                return enterPinText[0];
            else
                return enterPinText[1];
        }

        public static string getcardBlockedText(Language language)
        {
            if (language.Equals(Language.ENG))
                return cardBlockedText[0];
            else
                return cardBlockedText[1];
        }

        public static string getmenuWithDrawText(Language language)
        {
            if (language.Equals(Language.ENG))
                return menuWithDrawText[0];
            else
                return menuWithDrawText[1];
        }

        public static string getmenuBalanceText(Language language)
        {
            if (language.Equals(Language.ENG))
                return menuBalanceText[0];
            else
                return menuBalanceText[1];
        }

        public static string getmenuPinText(Language language)
        {
            if (language.Equals(Language.ENG))
                return menuPinText[0];
            else
                return menuPinText[1];
        }

        public static string getwrongPinText(Language language)
        {
            if (language.Equals(Language.ENG))
                return wrongPinText[0];
            else
                return wrongPinText[1];
        }

        public static string getchangePinText(Language language)
        {
            if (language.Equals(Language.ENG))
                return changePinText[0];
            else
                return changePinText[1];
        }

        public static string getotherAmountText(Language language)
        {
            if (language.Equals(Language.ENG))
                return otherAmountText[0];
            else
                return otherAmountText[1];
        }

        public static string getenterValueText(Language language)
        {
            if (language.Equals(Language.ENG))
                return enterValueText[0];
            else
                return enterValueText[1];
        }

        public static string getpinChangedText(Language language)
        {
            if (language.Equals(Language.ENG))
                return pinChangedText[0];
            else
                return pinChangedText[1];
        }

        public static string gettakeMoneyText(Language language)
        {
            if (language.Equals(Language.ENG))
                return takeMoneyText[0];
            else
                return takeMoneyText[1];
        }

        public static string getnotEnoughMoneyText(Language language)
        {
            if (language.Equals(Language.ENG))
                return notEnoughMoneyText[0];
            else
                return notEnoughMoneyText[1];
        }

        public static string getbadAmountText(Language language)
        {
            if (language.Equals(Language.ENG))
                return badAmountText[0];
            else
                return badAmountText[1];
        }
    }
}
