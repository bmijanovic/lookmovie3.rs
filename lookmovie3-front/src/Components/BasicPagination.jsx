import React from 'react';
import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';

const BasicPagination = ({ currentPage, pageSize, totalItems, onPageChange }) => {
  const totalPages = Math.ceil(totalItems / pageSize);

  const handlePageChange = (event, newPage) => {
    onPageChange(newPage);
  };

  return (
    <Stack spacing={2} sx={{alignItems: 'center'}}>
      <Pagination
        count={totalPages}
        page={currentPage}
        onChange={handlePageChange}
        color="primary"
      />
    </Stack>
  );
};

export default BasicPagination;
