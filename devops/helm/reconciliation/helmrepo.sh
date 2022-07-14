#!/bin/bash

# workout the absolute path to the checkout directory
abspath()
{
    case "${1}" in
        [./]*)
            local ABSPATH="$(cd ${1%/*}; pwd)/${1##*/}"
            echo "${ABSPATH}"
            ;;
        *)
            echo "${PWD}/${1}"
            ;;
    esac
}

die()
{
    message="$1";
    ${PRINTF} "Error: '%s'\n" "$message"
    exit 1
}


DATE_FMT="%Y-%m-%d"
SOURCE_DATE_EPOCH="${SOURCE_DATE_EPOCH:-$(date +%s)}"
BUILD_DATE=$(date -u -d "@$SOURCE_DATE_EPOCH" "+$DATE_FMT" 2>/dev/null || date -u -r "$SOURCE_DATE_EPOCH" "+$DATE_FMT" 2>/dev/null || date -u "+$DATE_FMT")

PRINTF=${PRINTF:-'/usr/bin/printf'}
SHELL=${SHELL:-'/bin/sh'}

abort_unless_set()
{
    env_name="$1";
    env_val="`printenv ${1}`"
    if [ -z "$env_val" ]; then
        printf "Fail: '%s' => '%s'\n" "$env_name" "$env_val"
        exit `false`
    else
        printf "Pass: '%s' => '%s'\n" "$env_name" "$env_val"
    fi
}

git_rev()
{
    tag=`git describe --tags $(git rev-list --tags --max-count=1)`
    echo ${tag}
}

GIT_REV=$(git_rev)
APPNAME=$(basename ${0})
SCRIPT=$(abspath ${0})
ROOTPATH=`dirname ${SCRIPT}`
DONE="[!] Done..."
export PROJECT_ROOT=${ROOTPATH}
export PRODUCT_NAME=$(grep '^name:' Chart.yaml| awk '{print $2}')
export PACKAGE_TAG="${GIT_REV}"
export PACKAGE_VERSION=${GIT_REV}
if [[ ${PACKAGE_VERSION}  =~ ^[a-zA-Z].* ]]; then
    export PACKAGE_VERSION=${PACKAGE_VERSION:1}
fi

export PACKAGE_TARNAME=${PRODUCT_NAME}-${PACKAGE_VERSION}.tgz


_process() {
    if [ $# -eq 0 ]; then
        echo -e "\nNot a valid command: \t $0 $1 "
         _show_help
    elif [ $# -eq 1 ] ; then
        while true; do
            case $1 in
                "-h" | "--help")
                    _show_help
                    shift
                    ;;
                "-b" | "--bundle")
                    _bundle_chart
                    shift
                    ;;
                "-p" | "--push")
                    _push_chart
                    shift
                    ;;
                * ) break ;;
            esac
        done
    else
        echo -e "not valid command $0 $1"
    fi
}


_bundle_chart()
{
    echo " project root ${PROJECT_ROOT}"

    # Update the openapi spec version
    if [ "$(uname)" == "Darwin" ]; then
        sed -e '/version:/ s/: .*/: '${PACKAGE_VERSION}'/' -i "" Chart.yaml
        sed -e '/appVersion:/ s/: .*/: '${PACKAGE_VERSION}'/' -i "" Chart.yaml
    else
        sed -e '/version:/ s/: .*/: '${PACKAGE_VERSION}'/' -i"" Chart.yaml
        sed -e '/appVersion:/ s/: .*/: '${PACKAGE_VERSION}'/' -i"" Chart.yaml
    fi

    helm package .
}

_push_chart()
{
    helm s3 push --force --relative ${PACKAGE_TARNAME} isb
}


_show_help() {
    echo -e "\nOverview: Release script \n" \
    'Usage: ./helmrepo.sh [options] \n\n' \
    '[options] \n\n' \
    '-h, --help                 Show amazing help :-) \n' \
    '-b, --bundle               Bundle informa deploy-chart \n' \
    '-p, --push                 Push helm chart to s3 repo \n' \
    '\tExample: helmrepo.sh -p \n\n'
}

abort_unless_set PRODUCT_NAME
abort_unless_set PACKAGE_VERSION
_process "$@"
