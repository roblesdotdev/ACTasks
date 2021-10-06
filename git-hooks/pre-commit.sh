#!/bin/sh

######## KT LINT-GRADLE HOOK START ########

CHANGED_FILES="$(git --no-pager diff --name-status --no-color --cached | awk '$1 != "D" && $2 ~ /\.kts|\.kt/ { print $2}')"

if [ -z "$CHANGED_FILES" ]; then
    echo "No Kotlin staged files."
    exit 0
fi;

echo "Running kt-lint over these files:"
echo "$CHANGED_FILES"

./gradlew --quiet ktlintFormat -PinternalKtlintGitFilter="$CHANGED_FILES"

echo "Completed kt-lint run."

echo "$CHANGED_FILES" | while read -r file; do
    if [ -f $file ]; then
        git add $file
    fi
done

echo "Completed kt-lint hook."
######## KT LINT-GRADLE HOOK END ########