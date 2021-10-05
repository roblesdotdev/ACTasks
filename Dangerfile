message "Thanks @#{github.pr_author}!"

if git.lines_of_code > 500
    warn "Please consider breaking up this pull request."
end

if github.pr_labels.empty?
    warn "Please add labels to this PR."
end

if git.deletions > git.insertions
    message  "🎉 Code Cleanup!"
end 
